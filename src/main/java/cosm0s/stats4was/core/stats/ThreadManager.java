package cosm0s.stats4was.core.stats;

import com.ibm.websphere.management.AdminClient;
import com.ibm.websphere.management.exception.ConnectorException;
import com.ibm.websphere.management.exception.ConnectorNotAvailableException;
import cosm0s.stats4was.core.connector.ManagementConnection;
import cosm0s.stats4was.core.exception.Stats4WasException;
import cosm0s.stats4was.core.threads.StatsCollector;
import cosm0s.stats4was.core.threads.observer.ThreadObserver;
import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.utils.DaemonContext;
import cosm0s.stats4was.utils.MBeansUtils;
import cosm0s.stats4was.xml.FindMetricsXml;

import javax.management.ObjectName;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadManager implements OptionLauncher {

    private ManagementConnection managementConnection;
    private AdminClient adminClient;
    private boolean started;
    private long intervalInMili;
    private FindMetricsXml findMetricsXml;

    @Override
    public void start() {
        this.init();
        while(this.started){
            try {
                long elapsetStart = System.currentTimeMillis();
                checkConnections();
                this.LaunchWorkers(this.createStatsCollectors());
                long delay = (System.currentTimeMillis() - elapsetStart);
                Thread.sleep(this.intervalInMili-delay);
            } catch (InterruptedException e) {
                this.started = false;
                L4j.getL4j().error("TreadManager sleep error: ", e);
            }
        }
    }

    public void init(){
        this.findMetricsXml = new FindMetricsXml();
        this.managementConnection.connect();
        this.adminClient = this.managementConnection.getConnector().getAdminClient();
        this.started = true;
        try {
            this.findMetricsXml.getAllMetricsConf();
            this.intervalInMili = Long.valueOf(DaemonContext.instance().getProperty("ElapsedThreadinterval")) * 1000;
            L4j.getL4j().debug("Elapsed thread interval: " + this.intervalInMili/1000 + " seconds");
        } catch (NumberFormatException ex) {
            //DEFAULT
            this.intervalInMili = 60000;
        } catch (Stats4WasException ex) {
            L4j.getL4j().error("Find metrics xml", ex);
        }
    }

    @Override
    public void setManagementConnection(ManagementConnection managementConnection){
        this.managementConnection = managementConnection;
    }

    private void LaunchWorkers(List<StatsCollector> statsCollectors){
        //Default
        int maxThreads = 5;
        try {
            maxThreads = Integer.valueOf(DaemonContext.instance().getProperty("MaxThreads"));
        } catch(NumberFormatException ex) {
            L4j.getL4j().info("Format error in MaxThreads conf");
        }
        ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
        for(StatsCollector statsCollector:statsCollectors){
            Runnable worker = statsCollector;
            executor.execute(worker);
        }
        executor.shutdown();
        try {
            if(executor.awaitTermination(Long.valueOf(DaemonContext.instance().getProperty("ElapsedThreadinterval")), TimeUnit.SECONDS)){
                L4j.getL4j().debug("IN TIME");
            } else {
                L4j.getL4j().info("OVER TIME");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<StatsCollector> createStatsCollectors() {
        List<StatsCollector> statsCollectors = new LinkedList<StatsCollector>();
        Set<ObjectName> webSphereRuntime = MBeansUtils.getAllServerRuntimes(this.adminClient);
        int threadCount = 1;
        for(ObjectName objectName: webSphereRuntime) {
            String node = objectName.getKeyProperty("node");
            if(!"dmgr".equals(node)) {
                StatsCollector statsCollector = new StatsCollector(objectName.getKeyProperty("name"), node, threadCount++);
                statsCollector.setManagementConnection(this.managementConnection);
                statsCollector.setFindMetricsXml(this.findMetricsXml);
                ThreadObserver threadObserver = new ThreadObserver();
                statsCollector.addObserver(threadObserver);
                statsCollectors.add(statsCollector);
            }
        }
        return statsCollectors;
    }

    public void checkConnections(){
        try {
            this.adminClient.isAlive();
        } catch (ConnectorException e) {
            if(e instanceof ConnectorNotAvailableException){
                this.managementConnection.connect();
            }
        }
    }

}

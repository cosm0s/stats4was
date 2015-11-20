package cosm0s.stats4was.core.stats;

import cosm0s.stats4was.core.connector.ManagementConnection;
import cosm0s.stats4was.core.threads.StatsCollector;
import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.utils.DaemonContext;

import java.util.LinkedList;
import java.util.List;

public class ThreadManager implements OptionLauncher {

    private ManagementConnection managementConnection;
    private List<StatsCollector> threads;

    @Override
    public void start() {
        this.managementConnection.connect();
        this.threads = this.createThreads();
        System.out.println(this.threads.size());
    }

    @Override
    public void setManagementConnection(ManagementConnection managementConnection){
        this.managementConnection = managementConnection;
    }

    private List<StatsCollector> createThreads() {
        List<StatsCollector> statsCollectors = new LinkedList<StatsCollector>();
        //Default
        int maxThreads = 5;
        try {
            maxThreads = Integer.valueOf(DaemonContext.instance().getProperty("MaxThreads"));
        } catch(NumberFormatException ex) {
            L4j.getL4j().info("Format error in MaxThreads conf");
        }
        return statsCollectors;
    }





}

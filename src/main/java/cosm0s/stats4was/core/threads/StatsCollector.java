package cosm0s.stats4was.core.threads;

import com.ibm.websphere.pmi.stat.WSStats;
import cosm0s.stats4was.core.connector.ManagementConnection;
import cosm0s.stats4was.core.statistics.StatisticsReader;
import cosm0s.stats4was.domain.Statistic;
import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.sender.Sender;
import cosm0s.stats4was.utils.MBeansUtils;
import cosm0s.stats4was.xml.FindMetricsXml;

import javax.management.ObjectName;
import java.util.List;
import java.util.Observable;

public class StatsCollector extends Observable implements Runnable {

    private List<Sender> senders;
    private int threadsNumber;
    private String name;
    private String node;
    private ManagementConnection managementConnection;
    private FindMetricsXml findMetricsXml;


    public StatsCollector(String name, String node, int threadsNumber){
        this.threadsNumber = threadsNumber;
        this.name = name;
        this.node = node;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Stats4Was-Thread-" + threadsNumber);
        L4j.getL4j().debug("Start thread: " + Thread.currentThread().getName() + " connection state: " + this.managementConnection.getConnector().getState());
        List<Statistic> statistics = getStatistics();
        if(statistics != null && statistics.size() >0) {
            for (Statistic statistic : getStatistics()) {
                for(Sender sender:this.senders){
                    sender.send(statistic);
                }
            }
        } else {
            L4j.getL4j().info("Start thread: " + Thread.currentThread().getName() + " don't get statistics");
        }
        L4j.getL4j().debug("End thread: " + Thread.currentThread().getName() + " connection state: " + this.managementConnection.getConnector().getState());
    }

    public List<Statistic> getStatistics() {
        ObjectName perfMBean = MBeansUtils.getPerfBean(this.managementConnection.getConnector().getAdminClient(), this.node, this.name );
        L4j.getL4j().debug("Perf Bean: " + String.valueOf(perfMBean));
        WSStats wsStats = MBeansUtils.getWSStats(this.managementConnection.getConnector().getAdminClient(), this.node, this.name);
        StatisticsReader statisticsReader = new StatisticsReader(perfMBean, wsStats);
        statisticsReader.setName(this.name);
        statisticsReader.setNode(this.node);
        statisticsReader.setFindMetricsXml(this.findMetricsXml);
        return statisticsReader.parser();
    }

    public void setSenders(List<Sender> senders) {
        this.senders = senders;
    }

    public void setManagementConnection(ManagementConnection managementConnection) {
        this.managementConnection = managementConnection;
    }

    public void setFindMetricsXml(FindMetricsXml findMetricsXml) {
        this.findMetricsXml = findMetricsXml;
    }
}

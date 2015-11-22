package cosm0s.stats4was.core.threads;

import com.ibm.websphere.pmi.stat.WSStats;
import cosm0s.stats4was.core.connector.ManagementConnection;
import cosm0s.stats4was.core.statistics.StatisticsReader;
import cosm0s.stats4was.domain.Statistic;
import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.sender.Sender;
import cosm0s.stats4was.utils.MBeansUtils;

import javax.management.ObjectName;
import java.util.List;
import java.util.Observable;

public class StatsCollector extends Observable implements Runnable {

    private StatisticsReader statisticsReader;
    private Sender sender;
    private int threadsNumber;
    private String name;
    private String node;
    private ManagementConnection managementConnection;


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
                //this.sender.send(statistic);
            }
        } else {
            L4j.getL4j().info("Start thread: " + Thread.currentThread().getName() + " don't get statistics");
        }
        L4j.getL4j().debug("End thread: " + Thread.currentThread().getName() + " connection state: " + this.managementConnection.getConnector().getState());
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setManagementConnection(ManagementConnection managementConnection) {
        this.managementConnection = managementConnection;
    }

    public List<Statistic> getStatistics() {
        ObjectName perfMBean = MBeansUtils.getPerfBean(this.managementConnection.getConnector().getAdminClient(), this.node, this.name );
        L4j.getL4j().debug("Perf Bean: " + String.valueOf(perfMBean));
        WSStats wsStats = MBeansUtils.getWSStats(this.managementConnection.getConnector().getAdminClient(), this.node, this.name);
        StatisticsReader statisticsReader = new StatisticsReader(perfMBean, wsStats, this.node, this.name);
        return statisticsReader.parser();
    }
}

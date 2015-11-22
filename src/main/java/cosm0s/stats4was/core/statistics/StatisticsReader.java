package cosm0s.stats4was.core.statistics;

import com.ibm.websphere.pmi.stat.WSStats;
import cosm0s.stats4was.domain.Statistic;
import cosm0s.stats4was.log.L4j;

import javax.management.ObjectName;
import java.util.LinkedList;
import java.util.List;

public class StatisticsReader {

    private ObjectName perfBean;
    private WSStats wsStats;
    private String node;
    private String name;

    public StatisticsReader(ObjectName perfMBean, WSStats wsStats, String node, String name){
        this.perfBean = perfMBean;
        this.wsStats = wsStats;
        this.node = node;
        this.name = name;
    }

    public List<Statistic> parser(){
        List<Statistic> stats = new LinkedList<Statistic>();
        if (this.wsStats != null) {

        } else {
            L4j.getL4j().warning("Node: " + this.node + " Server: " + this.name + " Not found stats");
        }
        return stats;
    }
}

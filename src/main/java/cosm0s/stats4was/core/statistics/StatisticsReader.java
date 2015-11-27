package cosm0s.stats4was.core.statistics;

import com.ibm.websphere.pmi.stat.WSStats;
import cosm0s.stats4was.domain.Statistic;
import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.xml.FindMetricsXml;

import javax.management.ObjectName;
import java.util.LinkedList;
import java.util.List;

public class StatisticsReader {

    private ObjectName perfBean;
    private WSStats wsStats;
    private String node;
    private String name;
    private FindMetricsXml findMetricsXml;

    public StatisticsReader(ObjectName perfMBean, WSStats wsStats){
        this.perfBean = perfMBean;
        this.wsStats = wsStats;
    }

    public List<Statistic> parser(){
        List<Statistic> stats = new LinkedList<Statistic>();
        if (this.wsStats != null) {

        } else {
            L4j.getL4j().warning("Node: " + this.node + " Server: " + this.name + " Not found stats");
        }
        return stats;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFindMetricsXml(FindMetricsXml findMetricsXml) {
        this.findMetricsXml = findMetricsXml;
    }
}

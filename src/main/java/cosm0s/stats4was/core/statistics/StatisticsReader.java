package cosm0s.stats4was.core.statistics;

import com.ibm.websphere.pmi.stat.WSStats;

import javax.management.ObjectName;
import java.util.List;

public class StatisticsReader {

    private ObjectName perfBean;
    private WSStats wsStats;

    public StatisticsReader(ObjectName perfMBean, WSStats wsStats){
        this.perfBean = perfMBean;
        this.wsStats = wsStats;
    }

    public List<String> parser(){
        return null;
    }
}

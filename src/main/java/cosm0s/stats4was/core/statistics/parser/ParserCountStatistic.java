package cosm0s.stats4was.core.statistics.parser;

import com.ibm.websphere.pmi.stat.WSCountStatistic;
import com.ibm.websphere.pmi.stat.WSStatistic;
import cosm0s.stats4was.domain.Statistic;
import cosm0s.stats4was.xml.mapping.XMLPMIStatsType;

import java.util.LinkedList;
import java.util.List;

public class ParserCountStatistic<E extends WSCountStatistic> extends AbstractParser<E> {

    private String prefix;
    private String node;
    private String metricName;

    public ParserCountStatistic(String prefix, String node, String metricName){
        this.prefix = prefix;
        this.node = node;
        this.metricName = metricName;
    }

    @Override
    public List<Statistic> getStatistic(WSStatistic wsStatistic, XMLPMIStatsType xmlpmiStatsType) {
        List<Statistic> statistics = new LinkedList<Statistic>();
        E parserStatistic = (E) wsStatistic;
        if(xmlpmiStatsType.getXmlCountStatistic() != null && xmlpmiStatsType.getXmlCountStatistic().isCount()){
            Statistic statistic = new Statistic();
            statistic.setMethod("count");
            statistic.setNode(this.node);
            statistic.setMetric(String.valueOf(parserStatistic.getCount()));
            statistic.setPrefix(this.prefix);
            statistic.setMetricName(this.metricName);
            statistic.setMetricSeparator(getMetricSeparator(xmlpmiStatsType));
            statistic.setUnity(getUnity(xmlpmiStatsType,parserStatistic));
            statistic.setTimesTamp(System.currentTimeMillis()/1000l);
            statistics.add(statistic);
        }
        return statistics;
    }

}

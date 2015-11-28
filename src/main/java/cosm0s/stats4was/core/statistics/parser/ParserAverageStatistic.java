package cosm0s.stats4was.core.statistics.parser;

import com.ibm.websphere.pmi.stat.WSAverageStatistic;
import com.ibm.websphere.pmi.stat.WSStatistic;
import cosm0s.stats4was.domain.Statistic;
import cosm0s.stats4was.xml.mapping.XMLPMIStatsType;

import java.util.LinkedList;
import java.util.List;

public class ParserAverageStatistic<E extends WSAverageStatistic> extends AbstractParser<E> {

    private String prefix;
    private String node;
    private String metricName;

    public ParserAverageStatistic(String prefix, String node, String metricName) {
        this.prefix = prefix;
        this.node = node;
        this.metricName = metricName;
    }

    @Override
    public List<Statistic> getStatistic(WSStatistic wsStatistic, XMLPMIStatsType xmlpmiStatsType) {
        List<Statistic> statistics = new LinkedList<Statistic>();
        E parserStatistic = (E) wsStatistic;
        if(xmlpmiStatsType.getXmlAverageStatistic() != null){
            if(xmlpmiStatsType.getXmlAverageStatistic().isCount()) {
                statistics.add(getStatistic("count", String.valueOf(parserStatistic.getCount()), xmlpmiStatsType, parserStatistic));
            }
            if(xmlpmiStatsType.getXmlAverageStatistic().isTotal()) {
                statistics.add(getStatistic("total", String.valueOf(parserStatistic.getTotal()), xmlpmiStatsType, parserStatistic));
            }
            if(xmlpmiStatsType.getXmlAverageStatistic().isMin()) {
                statistics.add(getStatistic("min", String.valueOf(parserStatistic.getMin()), xmlpmiStatsType, parserStatistic));
            }
            if(xmlpmiStatsType.getXmlAverageStatistic().isMax()) {
                statistics.add(getStatistic("max", String.valueOf(parserStatistic.getMax()), xmlpmiStatsType, parserStatistic));
            }

        }
        return statistics;
    }

    public Statistic getStatistic(String method, String metric, XMLPMIStatsType xmlpmiStatsType, E parserStatistic){
        Statistic statistic = new Statistic();
        statistic.setMethod(method);
        statistic.setNode(this.node);
        statistic.setMetric(metric);
        statistic.setPrefix(this.prefix);
        statistic.setMetricName(this.metricName);
        statistic.setMetricSeparator(getMetricSeparator(xmlpmiStatsType));
        statistic.setUnity(getUnity(xmlpmiStatsType, parserStatistic));
        statistic.setTimesTamp(System.currentTimeMillis() / 1000l);
        return statistic;
    }
}

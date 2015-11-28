package cosm0s.stats4was.core.statistics.parser;

import com.ibm.websphere.pmi.stat.WSBoundedRangeStatistic;
import com.ibm.websphere.pmi.stat.WSStatistic;
import cosm0s.stats4was.domain.Statistic;
import cosm0s.stats4was.xml.mapping.XMLPMIStatsType;

import java.util.LinkedList;
import java.util.List;

public class ParserBoundedRangeStatistic<E extends WSBoundedRangeStatistic> extends AbstractParser<E> {

    private String prefix;
    private String node;
    private String metricName;

    public ParserBoundedRangeStatistic(String prefix, String node, String metricName) {
        this.prefix = prefix;
        this.node = node;
        this.metricName = metricName;
    }

    @Override
    public List<Statistic> getStatistic(WSStatistic wsStatistic, XMLPMIStatsType xmlpmiStatsType) {
        List<Statistic> statistics = new LinkedList<Statistic>();
        E parserStatistic = (E) wsStatistic;
        if(xmlpmiStatsType.getXmlBoundedRangeStatistic() != null){
            if(xmlpmiStatsType.getXmlBoundedRangeStatistic().isUpperBound()) {
                statistics.add(getStatistic("upperBound", String.valueOf(parserStatistic.getUpperBound()), xmlpmiStatsType, parserStatistic));
            }
            if(xmlpmiStatsType.getXmlBoundedRangeStatistic().isUpperBound()) {
                statistics.add(getStatistic("lowebBound", String.valueOf(parserStatistic.getLowerBound()), xmlpmiStatsType, parserStatistic));
            }
            if(xmlpmiStatsType.getXmlBoundedRangeStatistic().isHighWaterMark()) {
                statistics.add(getStatistic("highWaterMark", String.valueOf(parserStatistic.getHighWaterMark()), xmlpmiStatsType, parserStatistic));
            }
            if(xmlpmiStatsType.getXmlBoundedRangeStatistic().isLowWaterMark()) {
                statistics.add(getStatistic("lowWaterMark", String.valueOf(parserStatistic.getLowWaterMark()), xmlpmiStatsType, parserStatistic));
            }
            if(xmlpmiStatsType.getXmlBoundedRangeStatistic().isCurrent()) {
                statistics.add(getStatistic("current", String.valueOf(parserStatistic.getCurrent()), xmlpmiStatsType, parserStatistic));
            }
            if(xmlpmiStatsType.getXmlBoundedRangeStatistic().isIntegral()) {
                statistics.add(getStatistic("integral", String.valueOf(parserStatistic.getIntegral()), xmlpmiStatsType, parserStatistic));
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

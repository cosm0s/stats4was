package cosm0s.stats4was.core.statistics.parser;

import com.ibm.websphere.pmi.stat.WSBoundaryStatistic;
import com.ibm.websphere.pmi.stat.WSStatistic;
import cosm0s.stats4was.domain.Statistic;
import cosm0s.stats4was.xml.mapping.XMLPMIStatsType;

import java.util.LinkedList;
import java.util.List;

public class ParserBoundaryStatistic<E extends WSBoundaryStatistic> extends AbstractParser<E> {

    private String prefix;
    private String node;
    private String metricName;

    public ParserBoundaryStatistic(String prefix, String node, String metricName) {
        this.prefix = prefix;
        this.node = node;
        this.metricName = metricName;
    }

    @Override
    public List<Statistic> getStatistic(WSStatistic wsStatistic, XMLPMIStatsType xmlpmiStatsType) {
        List<Statistic> statistics = new LinkedList<Statistic>();
        E parserStatistic = (E) wsStatistic;
        if(xmlpmiStatsType.getXmlBoundaryStatistic() != null){
            if(xmlpmiStatsType.getXmlBoundaryStatistic().isUpperBound()) {
                statistics.add(getStatistic("upperBound", String.valueOf(parserStatistic.getUpperBound()), xmlpmiStatsType, parserStatistic));
            }
            if(xmlpmiStatsType.getXmlBoundaryStatistic().isLowebBound()) {
                statistics.add(getStatistic("lowerbound", String.valueOf(parserStatistic.getLowerBound()), xmlpmiStatsType, parserStatistic));
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

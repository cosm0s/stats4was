package cosm0s.stats4was.core.statistics.parser;

import com.ibm.websphere.pmi.stat.WSDoubleStatistic;
import com.ibm.websphere.pmi.stat.WSStatistic;
import cosm0s.stats4was.domain.Statistic;
import cosm0s.stats4was.xml.mapping.XMLPMIStatsType;

import java.util.LinkedList;
import java.util.List;

public class ParserDoubleStatistic<E extends WSDoubleStatistic> extends AbstractParser<E> {

    private String prefix;
    private String node;
    private String metricName;

    public ParserDoubleStatistic(String prefix, String node, String metricName) {
        this.prefix = prefix;
        this.node = node;
        this.metricName = metricName;
    }

    @Override
    public List<Statistic> getStatistic(WSStatistic wsStatistic, XMLPMIStatsType xmlpmiStatsType) {
        List<Statistic> statistics = new LinkedList<Statistic>();
        E parserStatistic = (E) wsStatistic;
        if(xmlpmiStatsType.getXmlDoubleStatisc() != null && xmlpmiStatsType.getXmlDoubleStatisc().isCount()){
            Statistic statistic = new Statistic();
            statistic.setMethod("double");
            statistic.setNode(this.node);
            statistic.setMetric(String.valueOf(parserStatistic.getDouble()));
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

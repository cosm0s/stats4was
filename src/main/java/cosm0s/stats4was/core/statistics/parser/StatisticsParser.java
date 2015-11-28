package cosm0s.stats4was.core.statistics.parser;

import com.ibm.websphere.pmi.stat.WSAverageStatistic;
import com.ibm.websphere.pmi.stat.WSBoundaryStatistic;
import com.ibm.websphere.pmi.stat.WSBoundedRangeStatistic;
import com.ibm.websphere.pmi.stat.WSCountStatistic;
import com.ibm.websphere.pmi.stat.WSDoubleStatistic;
import com.ibm.websphere.pmi.stat.WSRangeStatistic;
import com.ibm.websphere.pmi.stat.WSStatistic;
import com.ibm.websphere.pmi.stat.WSTimeStatistic;
import cosm0s.stats4was.domain.Statistic;
import cosm0s.stats4was.utils.Utils;
import cosm0s.stats4was.xml.mapping.XMLPMIStatsType;

import java.util.LinkedList;
import java.util.List;

public class StatisticsParser {

    private enum StatisticType {COUNTSTATISTIC, DOUBLESTATISTIC, AVERAGESTATISTIC, TIMESTATISTIC, BOUNDARYSTATISTIC, RANGESTATISTIC, BOUNDEDRANGESTATISTIC};
    private String prefix;
    private String node;
    private String metricName;

    public StatisticsParser(String prefix, String node, String metricName){
        this.prefix = prefix;
        this.node = node;
        this.metricName = metricName;
    }

    public List<Statistic> parserStatistics(WSStatistic wsStatistic, XMLPMIStatsType xmlpmiStatsType){
        List<Statistic> statistics = new LinkedList<Statistic>();
        if (wsStatistic != null && (wsStatistic.getName() != null || this.metricName != null)) {
            String type = Utils.getWSStatisticType(wsStatistic);
            switch (StatisticType.valueOf(type.toUpperCase())) {
                case COUNTSTATISTIC:
                    ParserCountStatistic<WSCountStatistic> parserCountStatistic = new ParserCountStatistic<WSCountStatistic>(this.prefix, this.node, this.metricName);
                    statistics.addAll(parserCountStatistic.getStatistic(wsStatistic, xmlpmiStatsType));
                    break;
                case DOUBLESTATISTIC:
                    ParserDoubleStatistic<WSDoubleStatistic> parserDoubleStatistic = new ParserDoubleStatistic<WSDoubleStatistic>(this.prefix, this.node, this.metricName);
                    statistics.addAll(parserDoubleStatistic.getStatistic(wsStatistic, xmlpmiStatsType));
                    break;
                case AVERAGESTATISTIC:
                    ParserAverageStatistic<WSAverageStatistic> parserAverageStatistic = new ParserAverageStatistic<WSAverageStatistic>(this.prefix, this.node, this.metricName);
                    statistics.addAll(parserAverageStatistic.getStatistic(wsStatistic, xmlpmiStatsType));
                    break;
                case TIMESTATISTIC:
                    ParserTimeStatistic<WSTimeStatistic> parserTimeStatistic = new ParserTimeStatistic<WSTimeStatistic>(this.prefix, this.node, this.metricName);
                    statistics.addAll(parserTimeStatistic.getStatistic(wsStatistic, xmlpmiStatsType));
                    break;
                case BOUNDARYSTATISTIC:
                    ParserBoundaryStatistic<WSBoundaryStatistic> parserBoundaryStatistic = new ParserBoundaryStatistic<WSBoundaryStatistic>(this.prefix, this.node, this.metricName);
                    statistics.addAll(parserBoundaryStatistic.getStatistic(wsStatistic, xmlpmiStatsType));
                    break;
                case RANGESTATISTIC:
                    ParserRangeStatistic<WSRangeStatistic> parserRangeStatistic = new ParserRangeStatistic<WSRangeStatistic>(this.prefix, this.node, this.metricName);
                    statistics.addAll(parserRangeStatistic.getStatistic(wsStatistic, xmlpmiStatsType));
                    break;
                case BOUNDEDRANGESTATISTIC:
                    ParserBoundedRangeStatistic<WSBoundedRangeStatistic> parserBoundedRangeStatistic = new ParserBoundedRangeStatistic<WSBoundedRangeStatistic>(this.prefix, this.node, this.metricName);
                    statistics.addAll(parserBoundedRangeStatistic.getStatistic(wsStatistic, xmlpmiStatsType));
                    break;
            }
        } else if(wsStatistic != null){
            //l4j
        }
        return null;
    }



}

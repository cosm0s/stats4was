package cosm0s.stats4was.core.statistics.parser;

import com.ibm.websphere.pmi.stat.WSStatistic;
import cosm0s.stats4was.xml.mapping.XMLPMIStatsType;

public abstract class AbstractParser<E extends WSStatistic> implements ParserStatistic<E> {

    public String getMetricSeparator(XMLPMIStatsType xmlpmiStatsType){
        if(xmlpmiStatsType.getSeparateMetric()){
            return ".";
        } else {
            return "_";
        }
    }

    public String getUnity(XMLPMIStatsType xmlpmiStatsType, E statistic) {
        String unity = (xmlpmiStatsType.isUnit())?"_" + statistic.getUnit() + " ":" ";
        if("N/A".equals(statistic.getUnit()) || statistic.getUnit() == null) {
            unity = " ";
        }
        return unity.toLowerCase();
    }

}

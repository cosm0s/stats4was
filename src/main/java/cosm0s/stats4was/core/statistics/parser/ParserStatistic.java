package cosm0s.stats4was.core.statistics.parser;

import com.ibm.websphere.pmi.stat.WSStatistic;
import cosm0s.stats4was.domain.Statistic;
import cosm0s.stats4was.xml.mapping.XMLPMIStatsType;

import java.util.List;

public interface ParserStatistic<E extends WSStatistic> {

    public List<Statistic> getStatistic(WSStatistic wsStatistic, XMLPMIStatsType xmlpmiStatsType);

    public String getUnity(XMLPMIStatsType xmlpmiStatsType, E statistic);

}

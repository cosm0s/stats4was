package cosm0s.stats4was.core.statistics;

import com.ibm.websphere.pmi.stat.WSStatistic;
import com.ibm.websphere.pmi.stat.WSStats;
import cosm0s.stats4was.core.statistics.parser.StatisticsParser;
import cosm0s.stats4was.domain.Statistic;
import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.utils.Utils;
import cosm0s.stats4was.xml.FindMetricsXml;
import cosm0s.stats4was.xml.mapping.XMLMetric;
import cosm0s.stats4was.xml.mapping.XMLMetricGroup;

import javax.management.ObjectName;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StatisticsReader {

    private ObjectName perfBean;
    private WSStats wsStats;
    private String node;
    private String name;
    private FindMetricsXml findMetricsXml;
    private String prefix;

    public StatisticsReader(ObjectName perfMBean, WSStats wsStats){
        this.perfBean = perfMBean;
        this.wsStats = wsStats;
    }

    public List<Statistic> parser(){
        List<Statistic> stats = new LinkedList<Statistic>();
        if (this.wsStats != null) {
            for(XMLMetricGroup xmlMetricGroup:this.findMetricsXml.getXMLMetricGroup()){
                WSStats typeStats = wsStats.getStats(xmlMetricGroup.getXmlStatsType().getStatsType());
                if(typeStats != null){
                    stats.addAll(getStatsType(xmlMetricGroup, typeStats, true));
                } else {
                    L4j.getL4j().debug("Node: " + this.node + " Server: " + this.name + " Not found statstype " + xmlMetricGroup.getXmlStatsType().getStatsType());
                }
            }
        } else {
            L4j.getL4j().warning("Node: " + this.node + " Server: " + this.name + " Not found stats");
        }
        return stats;
    }

    private List<Statistic> getStatsType(XMLMetricGroup xmlMetricGroup, WSStats wsStats, boolean isInstance) {
        List<Statistic> stats = new LinkedList<Statistic>();
        List<Statistic> globalStats;
        List<Statistic> instances;
        this.prefix = this.perfBean.getKeyProperty("cell") + "." + this.perfBean.getKeyProperty("process") + "." + xmlMetricGroup.getPrefix();
//        globalStats = getGlobalStats(wsStats, metricGroup, this.prefix);
//        if(globalStats.size() > 0) {
//            result.addAll(globalStats);
//        }
        if(wsStats.getSubStats().length > 0){
            instances = getInstances(Arrays.asList(wsStats.getSubStats()), xmlMetricGroup, this.prefix, isInstance);
            if(instances.size() > 0){
                stats.addAll(instances);
            }
        }

        return stats;
    }

    public synchronized List<Statistic> getInstances(List<WSStats> wsStats, XMLMetricGroup xmlMetricGroup, String path, boolean isInstance) {
        List<Statistic> result = new LinkedList<Statistic>();
        for(WSStats substats: wsStats){
            String auxPath = path + "." + Utils.getParseBeanName(substats.getName());

            if (xmlMetricGroup.getXmlExclude() == null || !Utils.listContainsReg(xmlMetricGroup.getXmlExclude().getValue(), substats.getName())) {
                if (isInstance) {
                    if (xmlMetricGroup.getXmlInclude() != null && xmlMetricGroup.getXmlInclude().getValue() != null && xmlMetricGroup.getXmlInclude().getValue().size() > 0 && Utils.listContainsReg(xmlMetricGroup.getXmlInclude().getValue(), substats.getName())) {
                        if (xmlMetricGroup.getGlobal() && substats.getSubStats().length > 0) {
                            for (XMLMetric metric : xmlMetricGroup.getXmlMetrics().getMetric()) {
                                WSStatistic wsStatistic = substats.getStatistic(metric.getId());
                                if (wsStatistic != null) {
                                    String metricName = (metric.getName() == null || metric.getName().length() == 0) ? wsStatistic.getName() : metric.getName();
                                    StatisticsParser statisticsParser = new StatisticsParser(this.prefix, Utils.getHostByNode(this.node), metricName);
                                    result.addAll(statisticsParser.parserStatistics(wsStatistic, this.findMetricsXml.getXMLPMIStatsType()));
                                }
                            }
                        } else if (substats.getSubStats().length == 0) {
                            for (XMLMetric metric : xmlMetricGroup.getXmlMetrics().getMetric()) {
                                WSStatistic wsStatistic = substats.getStatistic(metric.getId());
                                if (wsStatistic != null) {
                                    String metricName = (metric.getName() == null || metric.getName().length() == 0) ? wsStatistic.getName() : metric.getName();
                                    StatisticsParser statisticsParser = new StatisticsParser(this.prefix, Utils.getHostByNode(this.node), metricName);
                                    result.addAll(statisticsParser.parserStatistics(wsStatistic, this.findMetricsXml.getXMLPMIStatsType()));
                                }
                            }
                        }
                        if (substats.getSubStats().length > 0) {
                            result.addAll(getInstances(Arrays.asList(substats.getSubStats()), xmlMetricGroup, auxPath, false));
                        }
                    } else if (xmlMetricGroup.getXmlInclude() == null || xmlMetricGroup.getXmlExclude().getValue() == null || xmlMetricGroup.getXmlExclude().getValue().size() == 0) {
                        if (xmlMetricGroup.getGlobal() && substats.getSubStats().length > 0) {
                            for (XMLMetric metric : xmlMetricGroup.getXmlMetrics().getMetric()) {
                                WSStatistic wsStatistic = substats.getStatistic(metric.getId());
                                if (wsStatistic != null) {
                                    String metricName = (metric.getName() == null || metric.getName().length() == 0) ? wsStatistic.getName() : metric.getName();
                                    StatisticsParser statisticsParser = new StatisticsParser(this.prefix, Utils.getHostByNode(this.node), metricName);
                                    result.addAll(statisticsParser.parserStatistics(wsStatistic, this.findMetricsXml.getXMLPMIStatsType()));
                                }
                            }
                        } else if (substats.getSubStats().length == 0) {
                            for (XMLMetric metric : xmlMetricGroup.getXmlMetrics().getMetric()) {
                                WSStatistic wsStatistic = substats.getStatistic(metric.getId());
                                if (wsStatistic != null) {
                                    String metricName = (metric.getName() == null || metric.getName().length() == 0) ? wsStatistic.getName() : metric.getName();
                                    StatisticsParser statisticsParser = new StatisticsParser(this.prefix, Utils.getHostByNode(this.node), metricName);
                                    result.addAll(statisticsParser.parserStatistics(wsStatistic, this.findMetricsXml.getXMLPMIStatsType()));
                                }
                            }
                        }
                        if (substats.getSubStats().length > 0) {
                            result.addAll(getInstances(Arrays.asList(substats.getSubStats()), xmlMetricGroup, auxPath, false));
                        }
                    }
                } else {
                    for (XMLMetric metric : xmlMetricGroup.getXmlMetrics().getMetric()) {
                        WSStatistic wsStatistic = substats.getStatistic(metric.getId());
                        if (wsStatistic != null) {
                            String metricName = (metric.getName() == null || metric.getName().length() == 0) ? wsStatistic.getName() : metric.getName();
                            StatisticsParser statisticsParser = new StatisticsParser(this.prefix, Utils.getHostByNode(this.node), metricName);
                            result.addAll(statisticsParser.parserStatistics(wsStatistic, this.findMetricsXml.getXMLPMIStatsType()));
                        }
                    }
                    if (substats.getSubStats().length > 0) {
                        result.addAll(getInstances(Arrays.asList(substats.getSubStats()), xmlMetricGroup, auxPath, false));
                    }
                }
            }
        }
        return result;
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

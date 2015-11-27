package cosm0s.stats4was.xml.mapping;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="MetricGroup")
public class XMLMetricGroup {

    private String prefix;
    private boolean unique;
    private boolean global;
    private XMLStatsType xmlStatsType;
    private XMLInclude xmlInclude;
    private XMLExclude xmlExclude;
    private XMLMetrics xmlMetrics;

    public String getPrefix() {
        return prefix;
    }

    @XmlAttribute(name = "prefix")
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public boolean isUnique() {
        return unique;
    }

    @XmlAttribute(name = "unique")
    public void setUnique(boolean unique) {
        this.unique = unique;

    }

    public boolean getGlobal() {
        return global;
    }

    @XmlAttribute(name = "global")
    public void setGlobal(boolean global) {
        this.global = global;
    }

    public XMLStatsType getXmlStatsType() {
        return xmlStatsType;
    }

    @XmlElement(name="StatsType")
    public void setXmlStatsType(XMLStatsType xmlStatsType) {
        this.xmlStatsType = xmlStatsType;
    }

    public XMLInclude getXmlInclude() {
        return xmlInclude;
    }

    @XmlElement(name="include")
    public void setXmlInclude(XMLInclude xmlInclude) {
        this.xmlInclude = xmlInclude;
    }

    public XMLExclude getXmlExclude() {
        return xmlExclude;
    }

    @XmlElement(name="exclude")
    public void setXmlExclude(XMLExclude xmlExclude) {
        this.xmlExclude = xmlExclude;
    }

    public XMLMetrics getXmlMetrics() {
        return xmlMetrics;
    }

    @XmlElement(name="Metrics")
    public void setXmlMetrics(XMLMetrics xmlMetrics) {
        this.xmlMetrics = xmlMetrics;
    }
}

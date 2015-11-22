package cosm0s.stats4was.xml.mapping;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Stats4was")
public class XMLStats4was {

    private XMLPMIStatsType xmlpmiStatsType;
    private XMLMetricGroups xmlMetricGroups;

    public XMLPMIStatsType getXmlpmiStatsType() {
        return xmlpmiStatsType;
    }

    @XmlElement(name="PMIStatsType")
    public void setXmlpmiStatsType(XMLPMIStatsType xmlpmiStatsType) {
        this.xmlpmiStatsType = xmlpmiStatsType;
    }

    public XMLMetricGroups getXmlMetricGroups() {
        return xmlMetricGroups;
    }

    @XmlElement(name="MetricGroups")
    public void setXmlMetricGroups(XMLMetricGroups xmlMetricGroups) {
        this.xmlMetricGroups = xmlMetricGroups;
    }
}

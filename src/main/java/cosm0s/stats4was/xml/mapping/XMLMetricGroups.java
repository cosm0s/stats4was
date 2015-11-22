package cosm0s.stats4was.xml.mapping;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="MetricGroups")
public class XMLMetricGroups {

    private List<XMLMetricGroup> xmlMetricGroups;

    public List<XMLMetricGroup> getXmlMetricGroups() {
        return xmlMetricGroups;
    }

    @XmlElement(name="MetricGroup")
    public void setXmlMetricGroups(List<XMLMetricGroup> xmlMetricGroups) {
        this.xmlMetricGroups = xmlMetricGroups;
    }
}

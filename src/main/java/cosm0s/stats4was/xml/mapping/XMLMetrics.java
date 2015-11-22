package cosm0s.stats4was.xml.mapping;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="Metrics")
public class XMLMetrics {

    private List<XMLMetric> metric;

    public List<XMLMetric> getMetric() {
        return metric;
    }

    @XmlElement(name="Metric")
    public void setMetric(List<XMLMetric> metric) {
        this.metric = metric;
    }

}

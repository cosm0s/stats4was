package cosm0s.stats4was.xml.mapping.pmi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="AverageStatistic")
public class XMLAverageStatistic {

    private boolean count;
    private boolean total;
    private boolean min;
    private boolean max;

    public boolean isCount() {
        return count;
    }

    @XmlAttribute(name = "count")
    public void setCount(boolean count) {
        this.count = count;
    }

    public boolean isTotal() {
        return total;
    }

    @XmlAttribute(name = "total")
    public void setTotal(boolean total) {
        this.total = total;
    }

    public boolean isMin() {
        return min;
    }

    @XmlAttribute(name = "min")
    public void setMin(boolean min) {
        this.min = min;
    }

    public boolean isMax() {
        return max;
    }

    @XmlAttribute(name = "max")
    public void setMax(boolean max) {
        this.max = max;
    }
}

package cosm0s.stats4was.xml.mapping.pmi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CountStatistic")
public class XMLCountStatistic {

    private boolean count;

    public boolean isCount() {
        return count;
    }

    @XmlAttribute(name = "count")
    public void setCount(boolean count) {
        this.count = count;
    }
}

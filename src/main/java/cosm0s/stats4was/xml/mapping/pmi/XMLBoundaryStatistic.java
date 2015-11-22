package cosm0s.stats4was.xml.mapping.pmi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BoundaryStatistic")
public class XMLBoundaryStatistic {

    private boolean upperBound;
    private boolean lowebBound;

    public boolean isUpperBound() {
        return upperBound;
    }

    @XmlAttribute(name = "upperBound")
    public void setUpperBound(boolean upperBound) {
        this.upperBound = upperBound;
    }

    public boolean isLowebBound() {
        return lowebBound;
    }
    @XmlAttribute(name = "lowebBound")
    public void setLowebBound(boolean lowebBound) {
        this.lowebBound = lowebBound;
    }
}

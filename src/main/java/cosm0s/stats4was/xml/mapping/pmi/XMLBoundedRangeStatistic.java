package cosm0s.stats4was.xml.mapping.pmi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BoundedRangeStatistic")
public class XMLBoundedRangeStatistic {

    private boolean upperBound;
    private boolean lowebBound;
    private boolean highWaterMark;
    private boolean lowWaterMark;
    private boolean current;
    private boolean integral;

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

    public boolean isHighWaterMark() {
        return highWaterMark;
    }

    @XmlAttribute(name = "highWaterMark")
    public void setHighWaterMark(boolean highWaterMark) {
        this.highWaterMark = highWaterMark;
    }

    public boolean isLowWaterMark() {
        return lowWaterMark;
    }

    @XmlAttribute(name = "lowWaterMark")
    public void setLowWaterMark(boolean lowWaterMark) {
        this.lowWaterMark = lowWaterMark;
    }

    public boolean isCurrent() {
        return current;
    }

    @XmlAttribute(name = "current")
    public void setCurrent(boolean current) {
        this.current = current;
    }

    public boolean isIntegral() {
        return integral;
    }

    @XmlAttribute(name = "integral")
    public void setIntegral(boolean integral) {
        this.integral = integral;
    }
}

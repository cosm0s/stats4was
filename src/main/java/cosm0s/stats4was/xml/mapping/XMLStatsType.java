package cosm0s.stats4was.xml.mapping;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="StatsType")
public class XMLStatsType {

    private String statsType;

    public String getStatsType() {
        return statsType;
    }

    @XmlValue
    public void setStatsType(String statsType) {
        this.statsType = statsType;
    }
}

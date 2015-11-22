package cosm0s.stats4was.xml.mapping;

import cosm0s.stats4was.xml.mapping.pmi.XMLAverageStatistic;
import cosm0s.stats4was.xml.mapping.pmi.XMLBoundaryStatistic;
import cosm0s.stats4was.xml.mapping.pmi.XMLBoundedRangeStatistic;
import cosm0s.stats4was.xml.mapping.pmi.XMLCountStatistic;
import cosm0s.stats4was.xml.mapping.pmi.XMLDoubleStatisc;
import cosm0s.stats4was.xml.mapping.pmi.XMLRangeStatistic;
import cosm0s.stats4was.xml.mapping.pmi.XMLTimeStatitic;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PMIStatsType")
public class XMLPMIStatsType {

    private boolean unit;
    private String unitseparator;
    private boolean separateMetric;
    private XMLCountStatistic xmlCountStatistic;
    private XMLDoubleStatisc xmlDoubleStatisc;
    private XMLAverageStatistic xmlAverageStatistic;
    private XMLBoundaryStatistic xmlBoundaryStatistic;
    private XMLRangeStatistic xmlRangeStatistic;
    private XMLTimeStatitic xmlTimeStatitic;
    private XMLBoundedRangeStatistic xmlBoundedRangeStatistic;

    public boolean isUnit() {
        return unit;
    }

    @XmlAttribute(name = "unit")
    public void setUnit(boolean unit) {
        this.unit = unit;
    }

    public String getUnitseparator() {
        return unitseparator;
    }

    @XmlAttribute(name = "unitseparator")
    public void setUnitseparator(String unitseparator) {
        this.unitseparator = unitseparator;
    }

    public boolean getSeparateMetric() {
        return separateMetric;
    }

    @XmlAttribute(name = "separateMetric")
    public void setSeparateMetric(boolean separateMetric) {
        this.separateMetric = separateMetric;
    }

    public XMLCountStatistic getXmlCountStatistic() {
        return xmlCountStatistic;
    }

    @XmlElement(name = "CountStatistic")
    public void setXmlCountStatistic(XMLCountStatistic xmlCountStatistic) {
        this.xmlCountStatistic = xmlCountStatistic;
    }

    public XMLDoubleStatisc getXmlDoubleStatisc() {
        return xmlDoubleStatisc;
    }

    @XmlElement(name = "DoubleStatisc")
    public void setXmlDoubleStatisc(XMLDoubleStatisc xmlDoubleStatisc) {
        this.xmlDoubleStatisc = xmlDoubleStatisc;
    }

    public XMLAverageStatistic getXmlAverageStatistic() {
        return xmlAverageStatistic;
    }

    @XmlElement(name = "AverageStatistic")
    public void setXmlAverageStatistic(XMLAverageStatistic xmlAverageStatistic) {
        this.xmlAverageStatistic = xmlAverageStatistic;
    }

    public XMLBoundaryStatistic getXmlBoundaryStatistic() {
        return xmlBoundaryStatistic;
    }

    @XmlElement(name = "BoundaryStatistic")
    public void setXmlBoundaryStatistic(XMLBoundaryStatistic xmlBoundaryStatistic) {
        this.xmlBoundaryStatistic = xmlBoundaryStatistic;
    }

    public XMLRangeStatistic getXmlRangeStatistic() {
        return xmlRangeStatistic;
    }

    @XmlElement(name = "RangeStatistic")
    public void setXmlRangeStatistic(XMLRangeStatistic xmlRangeStatistic) {
        this.xmlRangeStatistic = xmlRangeStatistic;
    }

    public XMLTimeStatitic getXmlTimeStatitic() {
        return xmlTimeStatitic;
    }

    @XmlElement(name = "TimeStatitic")
    public void setXmlTimeStatitic(XMLTimeStatitic xmlTimeStatitic) {
        this.xmlTimeStatitic = xmlTimeStatitic;
    }

    public XMLBoundedRangeStatistic getXmlBoundedRangeStatistic() {
        return xmlBoundedRangeStatistic;
    }

    @XmlElement(name = "BoundedRangeStatistic")
    public void setXmlBoundedRangeStatistic(XMLBoundedRangeStatistic xmlBoundedRangeStatistic) {
        this.xmlBoundedRangeStatistic = xmlBoundedRangeStatistic;
    }
}

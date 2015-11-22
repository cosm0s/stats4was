package cosm0s.stats4was.xml.mapping;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Metric")
public class XMLMetric {

    private int id;
    private String name;
    private int scale;
    private String scaleType;
    private XMLExpresion xmlExpresion;

    public int getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public int getScale() {
        return scale;
    }

    @XmlAttribute(name = "scale")
    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getScaleType() {
        return scaleType;
    }

    @XmlAttribute(name = "scaleType")
    public void setScaleType(String scaleType) {
        this.scaleType = scaleType;
    }

    public XMLExpresion getXmlExpresion() {
        return xmlExpresion;
    }

    @XmlElement(name="Expresion")
    public void setXmlExpresion(XMLExpresion xmlExpresion) {
        this.xmlExpresion = xmlExpresion;
    }
}

package cosm0s.stats4was.xml.mapping;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name="exclude")
public class XMLExclude {

    private List<String> value;

    public List<String> getValue() {
        return value;
    }

    @XmlValue
    public void setValue(String value) {
        this.value = Arrays.asList(value.split(","));
    }

}

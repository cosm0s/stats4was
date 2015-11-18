package cosm0s.stats4was.domain;

import cosm0s.stats4was.utils.Constants;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class BeanInfo {

    private Map<String,String> beansProperties;
    private static final List<String> order = new LinkedList<String>() {{
        add("name");
        add("type");
        add("cell");
        add("node");
        add("process");
        add("spec");
        add(" mbeanIdentifier");
        add("version");
        add("platform");
        add("j2eeType");
        add("J2EEServer");
        add("Server");
    }};

    public BeanInfo(Map<String,String> beansProperties){
        this.beansProperties = beansProperties;
    }

    public  String toStringConsole(){
        Map<String,String> beansBasicProperties = getBasicInfo();
        StringBuilder beanInfoBuilder = new StringBuilder();
        for(Map.Entry<String,String> properties: beansBasicProperties.entrySet()){
            if(properties.getValue() != null) {
                beanInfoBuilder.append(Constants.boldText + properties.getKey() + ":" + Constants.plainText + properties.getValue() + " ");
            }
        }
        for(Map.Entry<String,String> properties: beansProperties.entrySet()){
            if(properties.getValue() != null) {
                beanInfoBuilder.append(Constants.boldText + properties.getKey() + ":" + Constants.plainText + properties.getValue() + " ");
            }
        }
        return beanInfoBuilder.toString();
    }

    public Map<String,String> getBasicInfo(){
        Map<String,String> beansBasicProperties = new LinkedHashMap<String, String>();
        for(String key: order) {
            if (this.beansProperties.get(key) != null) {
                beansBasicProperties.put(key, this.beansProperties.get(key));
                this.beansProperties.remove(key);
            } else {
                beansBasicProperties.put(key, "null");
            }
        }
        return beansBasicProperties;
    }

}

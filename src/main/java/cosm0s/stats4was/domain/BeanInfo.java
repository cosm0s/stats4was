package cosm0s.stats4was.domain;

import java.util.Map;

public class BeanInfo {

    private Map<String,String> beansProperties;

    public BeanInfo(Map<String,String> beansProperties){
        this.beansProperties = beansProperties;
    }

    public void printConsole(){
        for(Map.Entry<String,String> properties: beansProperties.entrySet()){
            if(properties.getValue() != null) {
                System.out.print(properties.getKey() + ":" + properties.getValue() + " ");
            }
        }
    }
}

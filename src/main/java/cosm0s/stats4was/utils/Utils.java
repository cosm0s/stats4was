package cosm0s.stats4was.utils;

import com.ibm.websphere.pmi.stat.WSStatistic;

import java.util.List;

public class Utils {

    public static String getParseBeanName(String beanName){
        beanName = beanName.replace(".", "_");
        beanName = beanName.replace(" ", "_");
        beanName = beanName.replace("/", "_");
        beanName = beanName.replace(":", "_");
        beanName = beanName.replace(")", "");
        beanName = beanName.replace("(", "");
        return beanName;
    }

    public static boolean listContainsReg(List<String> listString, String regex){
        for(String string:listString){
            if(string.matches(regex)){
                return true;
            }
        }
        return false;
    }

    public static String getWSStatisticType(WSStatistic wsstatistic) {
        String chain = wsstatistic.toString().replace(" ", "");

        for(String type: chain.split("\\,")){
            String[] splitType = type.split("=");
            if("type".equals(splitType[0])) {
                return splitType[1];
            }
        }
        return "N/A";
    }
}

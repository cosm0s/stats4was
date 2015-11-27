package cosm0s.stats4was.utils;

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
}

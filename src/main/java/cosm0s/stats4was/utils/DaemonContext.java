package cosm0s.stats4was.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *  Daemon context load the basic properties.
 *  Also can contains runtime object for shared
 */
public class DaemonContext {

    private static DaemonContext context;
    private Properties properties;

    private DaemonContext(){}

    public static DaemonContext instance(){
        if(context == null){
            context = new DaemonContext();
            readFileProperties();
        }
        return context;
    }

    private static void readFileProperties(){
        try {
            FileInputStream fileInputStream = new FileInputStream(Constants.PropertiePath);
            context.properties = new Properties();
            context.properties.load(fileInputStream);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key){
        return this.properties.getProperty(key);
    }

    public void setProperty(String key, String value){
        this.properties.setProperty(key,value);
    }
}
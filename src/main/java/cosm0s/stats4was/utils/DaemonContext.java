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
            FileInputStream fileInputStream = new FileInputStream(Constants.PropertiePath + "stats4was.properties");
            context.properties = new Properties();
            context.properties.load(fileInputStream);
            if(context.getProperty("HostName") == null){
                context.setProperty("HostName", "localhost");
            }
            if(context.getProperty("Port") == null){
                context.setProperty("Port", "8879");
            }
            if(context.getProperty("Security") == null){
                context.setProperty("Security", "false");
            }
            if(context.getProperty("Connector") == null){
                context.setProperty("Connector", "soap");
            }
            if(context.getProperty("LogLevel") == null){
                context.setProperty("LogLevel", "info");
            }

            loadConnecorProperties();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void loadConnecorProperties() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(Constants.PropertiePath + context.getProperty("Connector") + ".properties");
        context.properties.load(fileInputStream);

    }
    public String getProperty(String key){
        return this.properties.getProperty(key);
    }

    public void setProperty(String key, String value){
        this.properties.setProperty(key,value);
    }
}
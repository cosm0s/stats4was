package cosm0s.stats4was.core;

import cosm0s.stats4was.core.connector.ManagementConnection;
import cosm0s.stats4was.core.exception.Stats4WasException;
import cosm0s.stats4was.core.stats.ListAttributes;
import cosm0s.stats4was.core.stats.ListBeans;
import cosm0s.stats4was.core.stats.ListOperation;
import cosm0s.stats4was.core.stats.OptionLauncher;
import cosm0s.stats4was.core.stats.ThreadManager;
import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.utils.CommonsCLI;
import cosm0s.stats4was.utils.Constants;

import java.util.Map;

public class Stats4Was {

    public static void main(String[] args) {

        Map<String, String> options = null;
        try {
            String option = null;
            options = CommonsCLI.parserArguments(args);
            ManagementConnection managementConnection = new ManagementConnection(options.get("host"), options.get("port"));
            if(options.get("option") != null){
                option = options.get("option");
            }
            OptionLauncher optionLauncher = null;
            if(option == null || option.isEmpty()){
                optionLauncher = new ThreadManager();
            } else if("lb".equals(option.toLowerCase())){
                optionLauncher = new ListBeans();
            } else if("lo".equals(option.toLowerCase())){
                optionLauncher = new ListOperation();
            } else if ("la".equals(option.toLowerCase())){
                optionLauncher = new ListAttributes();
            } else if ("v".equals(option.toLowerCase())){
                L4j.getL4j().info(Constants.appName + " v" + Constants.version);
            }
            if(optionLauncher != null) {
                L4j.getL4j().debug("Set management connection and start");
                optionLauncher.setManagementConnection(managementConnection);
                optionLauncher.start();
            }
            L4j.getL4j().info("Leaving stats4was...");

        } catch (Stats4WasException ex) {
            L4j.getL4j().error("Parsing arguments", ex);
        }
    }
}

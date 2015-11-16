package cosm0s.stats4was.core;

import cosm0s.stats4was.core.connector.AbstractConnector;
import cosm0s.stats4was.core.connector.ManagementConnection;
import cosm0s.stats4was.core.exception.Stats4WasException;
import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.utils.DaemonContext;


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
            if(option == null){

            } else if("lb".equals(option.toLowerCase())){

            } else if("lo".equals(option.toLowerCase())){

            } else if ("la".equals(option.toLowerCase())){

            }

        } catch (Stats4WasException ex) {
            L4j.getL4j().error("Parsing arguments", ex);
        }

    }
}

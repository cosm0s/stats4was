package cosm0s.stats4was.utils;

import cosm0s.stats4was.core.exception.Stats4WasException;
import cosm0s.stats4was.log.L4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

import java.util.HashMap;
import java.util.Map;

public class CommonsCLI {

    private CommonsCLI(){}

    public static Map<String,String> parserArguments(String[] args) throws Stats4WasException {

        L4j.getL4j().debug("Parsing args whit apache commons cli");
        Map<String, String> result = new HashMap<String, String>();

        CommandLineParser commandLineParser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();
        Options options = generateOptions();
        CommandLine commandLine;
        try {
            commandLine = commandLineParser.parse(options, args);
            int countUniqueArg = 0;
            if(commandLine.hasOption("h")){
                helpFormatter.printHelp("stats4was", options);
            }
            if (commandLine.hasOption("host")) {
                result.put("host", commandLine.getOptionValue("host"));
            }
            if (commandLine.hasOption("port")) {
                result.put("port", commandLine.getOptionValue("port"));
            }
            if (commandLine.hasOption("verbose")) {
                result.put("verbose", commandLine.getOptionValue("true"));
            }
            if (commandLine.hasOption("lb")) {
                result.put("option", "lb");
                countUniqueArg++;
            }
            if (commandLine.hasOption("lo")) {
                if (countUniqueArg == 0) {
                    result.put("option", "lo");
                    countUniqueArg++;
                } else {
                    L4j.getL4j().error("stats4was - Only can select one option, lb, lo, la or v");
                    helpFormatter.printHelp("stats4was", options);
                }
            }
            if (commandLine.hasOption("la")) {
                if (countUniqueArg == 0) {
                    result.put("option", "la");
                    countUniqueArg++;
                } else {
                    helpFormatter.printHelp("stats4was - Only can select one option, lb, lo, la or v", options);
                }
            }
            if (commandLine.hasOption("v")) {
                if (countUniqueArg == 0) {
                    result.put("option", "v");
                    countUniqueArg++;
                } else {
                    helpFormatter.printHelp("stats4was - Only can select one option, lb, lo, la or v", options);
                }
            }

        } catch (UnrecognizedOptionException ex) {
            helpFormatter.printHelp("stats4was - unrecognized argument", options);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static Options generateOptions(){
        Options options = new Options();
        options.addOption("lb", "list-beans", false, "Show list of server beans");
        options.addOption("lo", "list-bean-options", false, "Show options of beans");
        options.addOption("la", "list-bean-attributes", false, "Show attributes of beans");
        options.addOption("verbose", false, "verbose");
        options.addOption("host", true, "Force host. Host where DMGR is located");
        options.addOption("port", true, "Force port dmgr. Port to the DMGR server");
        options.addOption("v", "version", false, "Show  cellhealth-ng version");
        options.addOption("h", "help", false, "Show this help");
        return options;
    }
}

package cosm0s.stats4was.core;

import cosm0s.stats4was.core.exception.Stats4WasException;
import cosm0s.stats4was.log.L4j;
import org.apache.commons.cli.AmbiguousOptionException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
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

        try {
            CommandLine commandLine = commandLineParser.parse(generateOptions(), args);
            int countUniqueArg = 0;
            if (commandLine.hasOption("host")) {
                result.put("host", commandLine.getOptionValue("host"));
            }
            if (commandLine.hasOption("port")) {
                result.put("port", commandLine.getOptionValue("port"));
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
                    throw new Stats4WasException("Only can select one option");
                }
            }
            if (commandLine.hasOption("la")) {
                if (countUniqueArg == 0) {
                    result.put("option", "la");
                    countUniqueArg++;
                } else {
                    throw new Stats4WasException("Only can select one option");
                }
            }

        } catch (UnrecognizedOptionException ex) {
            L4j.getL4j().error("The unrecognized argument");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static Options generateOptions(){
        Options options = new Options();
        options.addOption("lb", "--list-beans", false, "List beans");
        options.addOption("lo", "--list-beans-operations", false, "List beans operations");
        options.addOption("la", "--list-beans-attributes", false, "List beans attributes");
        options.addOption("host", true, "force host dmgr");
        options.addOption("port", true, "force port dmgr");
        return options;
    }
}

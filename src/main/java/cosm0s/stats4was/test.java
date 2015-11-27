package cosm0s.stats4was;

import cosm0s.stats4was.core.exception.Stats4WasException;
import cosm0s.stats4was.xml.FindMetricsXml;

/**
 * Created by Alberto Pascual Corpas on 27/11/15.
 */
public class test {
    public static void main(String[] args) throws Stats4WasException {
        System.setProperty("config-path", "resources/conf/");
        FindMetricsXml m = new FindMetricsXml();
        m.getAllMetricsConf();
    }
}

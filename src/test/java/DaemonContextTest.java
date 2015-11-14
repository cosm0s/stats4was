import cosm0s.stats4was.utils.DaemonContext;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DaemonContextTest {

    @Test
    public void getDaemonContextHost(){

        //Set in the system properties config-path to the DaemonContext
        System.setProperty("config-path", "resources/conf/config.properties");

        assertNotNull(DaemonContext.instance().getProperty("was-host"));
        DaemonContext.instance().setProperty("test", "test");
        assertNotNull(DaemonContext.instance().getProperty("test"));

    }
}

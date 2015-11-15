package cosm0s.stats4was.core.connector;

import com.ibm.websphere.management.AdminClient;
import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.utils.DaemonContext;
import cosm0s.stats4was.utils.UtilsFile;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class SoapSecurityConnector extends AbstractConnector {

    public SoapSecurityConnector(){}

    @Override
    public Properties createProperties() {
        Properties properties = new Properties();
        properties.setProperty(AdminClient.CONNECTOR_TYPE, AdminClient.CONNECTOR_TYPE_SOAP);
        properties.setProperty(AdminClient.CONNECTOR_HOST, DaemonContext.instance().getProperty("was-host"));
        properties.setProperty(AdminClient.CONNECTOR_PORT, DaemonContext.instance().getProperty("was-port"));
        properties.setProperty(AdminClient.USERNAME, DaemonContext.instance().getProperty("was-user"));
        properties.setProperty(AdminClient.PASSWORD, DaemonContext.instance().getProperty("was-password"));
        if(checkAllFiles()) {
            properties.setProperty(AdminClient.CONNECTOR_SOAP_CONFIG, DaemonContext.instance().getProperty("was-soap.client.props"));
            properties.setProperty(AdminClient.CONNECTOR_SECURITY_ENABLED, DaemonContext.instance().getProperty("was-security"));
            properties.setProperty("javax.net.ssl.trustStore", DaemonContext.instance().getProperty("was-trustStore"));
            properties.setProperty("javax.net.ssl.keyStore", DaemonContext.instance().getProperty("was-keyStore"));
            properties.setProperty("javax.net.ssl.trustStorePassword", DaemonContext.instance().getProperty("was-trustStorePassword"));
            properties.setProperty("javax.net.ssl.keyStorePassword", DaemonContext.instance().getProperty("was-keyStorePassword"));
        } else {
            L4j.getL4j().error("Can't connect to WebSphere dmgr. Any file don't exist");
            return null;
        }
        return properties;
    }

    private boolean checkAllFiles(){
        List<String> filePaths = new LinkedList<String>();
        filePaths.add(DaemonContext.instance().getProperty("was-soap.client.props"));
        filePaths.add(DaemonContext.instance().getProperty("was-trustStore"));
        filePaths.add(DaemonContext.instance().getProperty("was-keyStore"));
        return UtilsFile.checkFile(filePaths);
    }

    public AdminClient getAdminClient() {
        return adminClient;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

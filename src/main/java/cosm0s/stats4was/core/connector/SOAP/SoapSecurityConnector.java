package cosm0s.stats4was.core.connector.SOAP;

import com.ibm.websphere.management.AdminClient;
import cosm0s.stats4was.core.connector.AbstractConnector;
import cosm0s.stats4was.core.exception.Stats4WasException;
import cosm0s.stats4was.utils.DaemonContext;
import cosm0s.stats4was.utils.UtilsFile;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class SoapSecurityConnector extends AbstractConnector {

    private String host;
    private String port;

    public SoapSecurityConnector(String host, String port){
        this.host = host;
        this.port = port;
    }

    @Override
    public Properties createProperties() throws Stats4WasException {
        Properties properties = new Properties();
        properties.setProperty(AdminClient.CONNECTOR_TYPE, AdminClient.CONNECTOR_TYPE_SOAP);
        properties.setProperty(AdminClient.CONNECTOR_HOST, (this.host != null)? this.host:DaemonContext.instance().getProperty("HostName"));
        properties.setProperty(AdminClient.CONNECTOR_PORT, (this.port != null)? this.port:DaemonContext.instance().getProperty("Port"));
        properties.setProperty(AdminClient.USERNAME, DaemonContext.instance().getProperty("SoapUser"));
        properties.setProperty(AdminClient.PASSWORD, DaemonContext.instance().getProperty("SoapPassword"));
        if(checkAllFiles()) {
            properties.setProperty(AdminClient.CONNECTOR_SOAP_CONFIG, DaemonContext.instance().getProperty("SoapClientProps"));
            properties.setProperty(AdminClient.CONNECTOR_SECURITY_ENABLED, "true");
            properties.setProperty("javax.net.ssl.trustStore", DaemonContext.instance().getProperty("SoapTrusStore"));
            properties.setProperty("javax.net.ssl.keyStore", DaemonContext.instance().getProperty("SoapKeyStore"));
            properties.setProperty("javax.net.ssl.trustStorePassword", DaemonContext.instance().getProperty("SoapTrustStorePassword"));
            properties.setProperty("javax.net.ssl.keyStorePassword", DaemonContext.instance().getProperty("SoapKeyStorePassword"));
        } else {
            throw new Stats4WasException("Can't connect to WebSphere dmgr. Any file don't exist");
        }
        return properties;
    }

    private boolean checkAllFiles(){
        List<String> filePaths = new LinkedList<String>();
        filePaths.add(DaemonContext.instance().getProperty("SoapClientProps"));
        filePaths.add(DaemonContext.instance().getProperty("SoapTrusStore"));
        filePaths.add(DaemonContext.instance().getProperty("SoapKeyStore"));
        return UtilsFile.checkFile(filePaths);
    }

}

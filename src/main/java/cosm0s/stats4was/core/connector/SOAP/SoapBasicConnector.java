package cosm0s.stats4was.core.connector.SOAP;

import com.ibm.websphere.management.AdminClient;
import cosm0s.stats4was.core.connector.AbstractConnector;
import cosm0s.stats4was.utils.DaemonContext;
import cosm0s.stats4was.utils.UtilsFile;

import java.util.Properties;

public class SoapBasicConnector extends AbstractConnector {

    private String host;
    private String port;

    public SoapBasicConnector(String host, String port){
        this.host = host;
        this.port = port;
    }

    @Override
    public Properties createProperties() {
        Properties properties = new Properties();
        properties.setProperty(AdminClient.CONNECTOR_TYPE, AdminClient.CONNECTOR_TYPE_SOAP);
        properties.setProperty(AdminClient.CONNECTOR_HOST, (this.host != null)? this.host: DaemonContext.instance().getProperty("HostName"));
        properties.setProperty(AdminClient.CONNECTOR_PORT, (this.port != null)? this.port:DaemonContext.instance().getProperty("Port"));
        properties.setProperty(AdminClient.USERNAME, DaemonContext.instance().getProperty("SoapUser"));
        properties.setProperty(AdminClient.PASSWORD, DaemonContext.instance().getProperty("SoapPassword"));
        properties.setProperty(AdminClient.CONNECTOR_SECURITY_ENABLED, "false");
        if(UtilsFile.checkFile(DaemonContext.instance().getProperty("SoapClientProps"))) {
            properties.setProperty(AdminClient.CONNECTOR_SOAP_CONFIG, DaemonContext.instance().getProperty("SoapClientProps"));
        }
        return properties;
    }

}

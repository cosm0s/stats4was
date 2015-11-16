package cosm0s.stats4was.core.connector;

import cosm0s.stats4was.core.connector.SOAP.SoapConnectorFactory;
import cosm0s.stats4was.core.exception.Stats4WasException;
import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.utils.DaemonContext;

public class ManagementConnection {

    private String host;
    private String port;
    private AbstractConnector abstractConnector;


    public ManagementConnection(String host, String port){
        this.host = host;
        this.port = port;
    }

    public void connect(){
        AbstractConnector abstractConnector = null;
        try {
            do {
                if ("soap".equalsIgnoreCase(DaemonContext.instance().getProperty("Connector"))) {
                    SoapConnectorFactory soapConnectorFactory = new SoapConnectorFactory(this.host, this.port);
                    if (Boolean.valueOf(DaemonContext.instance().getProperty("Security"))) {
                        abstractConnector = soapConnectorFactory.createSecurityConnector();
                    } else {
                        abstractConnector = soapConnectorFactory.createBasicConnector();
                    }
                }
                abstractConnector.connect();
                if(abstractConnector == null || abstractConnector.adminClient == null) {
                    L4j.getL4j().info("Connection WebSphere dmgr timeout.");
                    L4j.getL4j().info("Reconnect in " + DaemonContext.instance().getProperty("Timeout") + " seconds");
                    Thread.sleep(Long.valueOf(DaemonContext.instance().getProperty("Timeout")) * 1000l);
                } else {
                    L4j.getL4j().debug("Connected to dmgr:" + DaemonContext.instance().getProperty("HostName") + ":" + DaemonContext.instance().getProperty("Port"));
                }

            } while(abstractConnector == null || abstractConnector.adminClient == null);
        } catch (Stats4WasException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.abstractConnector = abstractConnector;
    }

    public AbstractConnector getConnector(){
        return this.abstractConnector;
    }
}

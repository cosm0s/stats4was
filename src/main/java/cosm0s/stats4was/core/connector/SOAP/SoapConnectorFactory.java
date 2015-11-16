package cosm0s.stats4was.core.connector.SOAP;

public class SoapConnectorFactory {

    private String host;
    private String port;

    public SoapConnectorFactory(String host, String port){
        this.host = host;
        this.port = port;
    }

    public SoapSecurityConnector createSecurityConnector(){
        return new SoapSecurityConnector(this.host, this.port);
    }

    public SoapBasicConnector createBasicConnector(){
        return new SoapBasicConnector(this.host, this.port);
    }
}

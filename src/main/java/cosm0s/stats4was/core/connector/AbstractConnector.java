package cosm0s.stats4was.core.connector;

import com.ibm.websphere.management.AdminClient;
import com.ibm.websphere.management.AdminClientFactory;
import com.ibm.websphere.management.exception.ConnectorException;
import cosm0s.stats4was.core.exception.Stats4WasException;
import cosm0s.stats4was.log.L4j;

import java.util.Properties;

public abstract class AbstractConnector {

    protected enum State{CONNECTED,CLOSED,UNCONNECTED}

    protected AdminClient adminClient;
    protected State state;

    public AbstractConnector(){}

    public void connect() throws Stats4WasException {
        Properties properties = createProperties();
        if(properties != null) {
            this.createAdminClient(properties);
            if(this.adminClient != null) {
                this.state = State.CONNECTED;
            } else {
                this.state = State.UNCONNECTED;
            }
        } else {
            this.state = State.UNCONNECTED;
        }
    }

    public void createAdminClient(Properties properties){
        try {
            this.adminClient = AdminClientFactory.createAdminClient(properties);
        } catch (ConnectorException ex) {
            L4j.getL4j().error("Can't create connector AdminClient");
        }
    }

    public abstract Properties createProperties() throws Stats4WasException;

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

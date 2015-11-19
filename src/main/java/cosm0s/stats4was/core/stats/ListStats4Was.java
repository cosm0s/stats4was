package cosm0s.stats4was.core.stats;

import com.ibm.websphere.management.exception.ConnectorException;
import cosm0s.stats4was.core.connector.ManagementConnection;

import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanInfo;
import javax.management.ObjectName;
import javax.management.ReflectionException;

public abstract class ListStats4Was implements OptionLauncher {

    private ManagementConnection managementConnection;

    @Override
    public void start() {
        this.managementConnection.connect();
        this.showInL4j();
    }

    @Override
    public void setManagementConnection(ManagementConnection managementConnection){
        this.managementConnection = managementConnection;
    }

    protected abstract void showInL4j();

    public ManagementConnection getManagementConnection() {
        return managementConnection;
    }

    protected MBeanInfo getBean(ObjectName objectName){
        MBeanInfo mbeanInfo = null;
        try {
            mbeanInfo = getManagementConnection().getConnector().getAdminClient().getMBeanInfo(objectName);
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (ReflectionException e) {
            e.printStackTrace();
        } catch (ConnectorException e) {
            e.printStackTrace();
        }
        return mbeanInfo;
    }
}

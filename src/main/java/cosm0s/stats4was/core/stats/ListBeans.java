package cosm0s.stats4was.core.stats;

import cosm0s.stats4was.core.connector.ManagementConnection;
import cosm0s.stats4was.domain.BeanInfo;
import cosm0s.stats4was.utils.MBeansUtils;

import javax.management.ObjectName;
import java.util.LinkedList;
import java.util.List;


public class ListBeans implements OptionLauncher {

    private ManagementConnection managementConnection;
    private static final String query = "*:*";

    public ListBeans(){}

    @Override
    public void start() {
        this.managementConnection.connect();
        MBeansUtils mbeansUtils = new MBeansUtils(this.managementConnection.getConnector().getAdminClient());
        List<BeanInfo> beanInfos = new LinkedList<BeanInfo>();
        for(ObjectName objectName: mbeansUtils.getMBeans(query)){
            beanInfos.add(new BeanInfo(objectName.getKeyPropertyList()));
        }
        for(BeanInfo beanInfo:beanInfos){
            System.out.println();
            beanInfo.printConsole();
            System.out.println();
        }
    }

    @Override
    public void setManagementConnection(ManagementConnection managementConnection){
        this.managementConnection = managementConnection;
    }

}

package cosm0s.stats4was.core.stats;

import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.utils.Constants;
import cosm0s.stats4was.utils.MBeansUtils;
import cosm0s.stats4was.utils.UtilsFile;

import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ObjectName;
import java.io.File;
import java.io.IOException;

public class ListOperation extends ListStats4Was {

    private static final String name = "listOperation";

    protected void showInL4j(){
        int countBeans = 0;
        try {
            File file = UtilsFile.getFile(name);
            for(ObjectName objectName: MBeansUtils.getMBeans(getManagementConnection().getConnector().getAdminClient(), Constants.queryAll)){
                MBeanInfo mbeanInfo = getBean(objectName);
                if(mbeanInfo.getOperations().length >= 1) {
                    UtilsFile.print("#########################################", file);
                    UtilsFile.print("# ObjectName " + objectName, file);
                    UtilsFile.print("# Operations: ", file);
                    for (MBeanOperationInfo mbeanOperationInfo : mbeanInfo.getOperations()) {
                        UtilsFile.print("# \t --------------------------------------------", file);
                        UtilsFile.print("# \t | Name: " + mbeanOperationInfo.getName(), file);
                        UtilsFile.print("# \t | Return type: " + mbeanOperationInfo.getReturnType(), file);
                        UtilsFile.print("# \t | Description: " + mbeanOperationInfo.getDescription(), file);
                    }
                    countBeans++;
                }
            }
            UtilsFile.print("##################################", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        L4j.getL4j().info(Constants.boldText + countBeans + Constants.plainText + " Beans have been printed with operations");
    }

}

package cosm0s.stats4was.core.stats;

import cosm0s.stats4was.domain.BeanInfo;
import cosm0s.stats4was.log.L4j;
import cosm0s.stats4was.utils.Constants;
import cosm0s.stats4was.utils.MBeansUtils;
import cosm0s.stats4was.utils.UtilsFile;

import javax.management.ObjectName;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ListBeans extends ListStats4Was {

    private static final String name = "listBeans";

    public ListBeans(){}

    protected void showInL4j(){
        List<BeanInfo> beanInfos = new LinkedList<BeanInfo>();
        for(ObjectName objectName: MBeansUtils.getMBeans(getManagementConnection().getConnector().getAdminClient(), Constants.queryAll)){
            beanInfos.add(new BeanInfo(objectName.getKeyPropertyList()));
        }
        try {
            File file = UtilsFile.getFile(name);
            int countBeans = 0;
            for(BeanInfo beanInfo:beanInfos){
                UtilsFile.print(beanInfo.toStringConsole(), file);
                countBeans++;
            }
            L4j.getL4j().info(Constants.boldText + countBeans + Constants.plainText + " Beans have been printed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

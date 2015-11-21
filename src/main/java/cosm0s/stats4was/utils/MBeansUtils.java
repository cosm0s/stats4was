package cosm0s.stats4was.utils;

import com.ibm.websphere.management.AdminClient;
import com.ibm.websphere.management.exception.ConnectorException;
import com.ibm.websphere.management.exception.ConnectorNotAvailableException;
import com.ibm.websphere.pmi.stat.WSStats;
import com.ibm.ws.webservices.engine.utils.Admin;
import cosm0s.stats4was.log.L4j;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class MBeansUtils {

    public static Set<ObjectName> getMBeans(AdminClient adminClient, String query) {
        if(adminClient == null) {
            throw new NullPointerException();
        }
        Set objects = null;
        try {
            objects = adminClient.queryNames(new ObjectName(query), null);
        } catch (ConnectorNotAvailableException e) {
            L4j.getL4j().warning("Connector not available");
        } catch (ConnectorException e) {
            L4j.getL4j().error("Error in connector", e);
        } catch (MalformedObjectNameException e) {
            L4j.getL4j().error("Object malformed", e);
        }
        return castSetObjectNames(objects);
    }

    public static Set<ObjectName> castSetObjectNames(Set objecs){
        Set<ObjectName> objectNames = new LinkedHashSet<ObjectName>();
        for(Object object: objecs){
            objectNames.add((ObjectName)object);
        }
        return objectNames;
    }

    public static ObjectName getMBean(AdminClient adminClient, String query) {
        Set mbeans = getMBeans(adminClient, query);
        ObjectName objectName = null;
        if(mbeans != null && mbeans.size() > 0) {
            objectName = (ObjectName) mbeans.iterator().next();
        }
        return objectName;
    }

    public static Set<ObjectName> getAllServerRuntimes(AdminClient adminClient)  {
            return getMBeans(adminClient, "WebSphere:*,type=Server,j2eeType=J2EEServer");
    }

    public static Map<String,String> getPathHostChStats(AdminClient adminClient){
        Map<String,String> pathChstats = new HashMap<String, String>();
        ObjectName dmgr = getMBean(adminClient, "WebSphere:processType=DeploymentManager,*");
        String cell = dmgr.getKeyProperty("cell");
        if(cell != null) {
            String query = "WebSphere:processType=ManagedProcess,cell=" + cell + ",*";
            String chStatsNode;
            String chStatsHost;
            String chStatsCell;
            String path = "";
            String host;
            for (ObjectName objectName : getMBeans(adminClient, query)) {
                chStatsNode = objectName.getKeyProperty("node");
                chStatsHost = getHostByNode(chStatsNode);
                chStatsCell = objectName.getKeyProperty("cell");
                if (path.length() == 0) {
                    if ((chStatsNode != null && chStatsNode.length() > 0) && (chStatsHost != null && chStatsHost.length() > 0) && (chStatsCell != null && chStatsCell.length() > 0)) {
                        path = chStatsCell + ".ch_stats";
                        host = chStatsHost;
                        pathChstats.put("path", path);
                        pathChstats.put("host", host);
                    }
                }
            }
        }
        return pathChstats;
    }

    public static WSStats getWSStats(AdminClient adminClient, String node, String name) {
        WSStats wsStats = null;
        ObjectName serverPerfMBean = getMBean(adminClient, "WebSphere:type=Perf,node=" + node + ",process=" + name + ",*");
        ObjectName objectName = getMBean(adminClient, "WebSphere:name=" + name + ",node=" + node + ",process=" + name + ",type=Server,*");
        if (objectName != null) {
            String[] signature = new String[]{"javax.management.ObjectName", "java.lang.Boolean"};
            Object[] params = new Object[]{objectName, true};
            try {
                wsStats = (WSStats) adminClient.invoke(serverPerfMBean, "getStatsObject", params, signature);
            } catch (Exception e) {
                L4j.getL4j().error("Capturer ", e);
            }
        }
        return wsStats;
    }

    public static ObjectName getPerfBean(AdminClient adminClient, String node, String name){
        return getMBean(adminClient, "WebSphere:type=Perf,node=" + node + ",process=" + name + ",*");
    }

    public static String getHostByNode(String node){
        node = node.toLowerCase();
        String[] nodeSplit = node.split("node");
        return nodeSplit[0];
    }
}
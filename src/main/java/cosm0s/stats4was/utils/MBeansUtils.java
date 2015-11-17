package cosm0s.stats4was.utils;

import com.ibm.websphere.management.AdminClient;
import com.ibm.websphere.management.exception.ConnectorException;
import com.ibm.websphere.management.exception.ConnectorNotAvailableException;
import com.ibm.websphere.pmi.stat.WSStats;
import cosm0s.stats4was.log.L4j;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class MBeansUtils {

    private AdminClient adminClient;


    public MBeansUtils(AdminClient adminClient) {
        this.adminClient = adminClient;
    }


    public Set<ObjectName> getMBeans(String query) {
        if(this.adminClient == null) {
            throw new NullPointerException();
        }
        Set objects = null;
        try {
            objects = this.adminClient.queryNames(new ObjectName(query), null);
        } catch (ConnectorNotAvailableException e) {
            L4j.getL4j().warning("Connector not available");
        } catch (ConnectorException e) {
            String[] classNameSplit = this.getClass().getName().split("\\.");
            L4j.getL4j().error(classNameSplit[classNameSplit.length-1] + ", " + "Error in connector", e);
        } catch (MalformedObjectNameException e) {
            String[] classNameSplit = this.getClass().getName().split("\\.");
            L4j.getL4j().error(classNameSplit[classNameSplit.length-1] + ", " + "Object malformed", e);
        }
        return this.castSetObjectNames(objects);
    }

    public Set<ObjectName> castSetObjectNames(Set objecs){
        Set<ObjectName> objectNames = new LinkedHashSet<ObjectName>();
        for(Object object: objecs){
            objectNames.add((ObjectName)object);
        }
        return objectNames;
    }

    public ObjectName getMBean(String query) {
        Set mbeans = this.getMBeans(query);
        ObjectName objectName = null;
        if(mbeans != null && mbeans.size() > 0) {
            objectName = (ObjectName) mbeans.iterator().next();
        }
        return objectName;
    }

    public AdminClient getClient(){
        return this.adminClient;
    }


    public Set<ObjectName> getAllServerRuntimes()  {
            return this.getMBeans("WebSphere:*,type=Server,j2eeType=J2EEServer");
    }

    public Map<String,String> getPathHostChStats(){
        Map<String,String> pathChstats = new HashMap<String, String>();
        ObjectName dmgr = this.getMBean("WebSphere:processType=DeploymentManager,*");
        String cell = dmgr.getKeyProperty("cell");
        if(cell != null) {
            String query = "WebSphere:processType=ManagedProcess,cell=" + cell + ",*";
            String chStatsNode;
            String chStatsHost;
            String chStatsCell;
            String path = "";
            String host;
            for (ObjectName objectName : this.getMBeans(query)) {
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

    public WSStats getWSStats(String node, String serverName) {
        WSStats wsStats = null;
        ObjectName serverPerfMBean = this.getMBean("WebSphere:type=Perf,node=" + node + ",process=" + serverName + ",*");
        ObjectName objectName = this.getMBean("WebSphere:name=" + serverName + ",node=" + node + ",process=" + serverName + ",type=Server,*");
        if (objectName != null) {
            String[] signature = new String[]{"javax.management.ObjectName", "java.lang.Boolean"};
            Object[] params = new Object[]{objectName, true};
            try {
                wsStats = (WSStats) this.adminClient.invoke(serverPerfMBean, "getStatsObject", params, signature);
            } catch (Exception e) {
                L4j.getL4j().error("Capturer ", e);
            }
        }
        return wsStats;
    }

    public ObjectName getPerfBean(String node, String serverName){
        return this.getMBean("WebSphere:type=Perf,node=" + node + ",process=" + serverName + ",*");
    }

    public static String getHostByNode(String node){
        node = node.toLowerCase();
        String[] nodeSplit = node.split("node");
        return nodeSplit[0];
    }
}
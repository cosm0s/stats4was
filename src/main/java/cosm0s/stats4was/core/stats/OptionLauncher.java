package cosm0s.stats4was.core.stats;

import cosm0s.stats4was.core.connector.ManagementConnection;

public interface OptionLauncher {

    public void start();

    public void setManagementConnection(ManagementConnection managementConnection);
}

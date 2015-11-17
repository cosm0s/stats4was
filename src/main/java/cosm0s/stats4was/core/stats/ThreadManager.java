package cosm0s.stats4was.core.stats;

import cosm0s.stats4was.core.connector.ManagementConnection;

public class ThreadManager implements OptionLauncher {

    private ManagementConnection managementConnection;

    @Override
    public void start() {

    }

    @Override
    public void setManagementConnection(ManagementConnection managementConnection){
        this.managementConnection = managementConnection;
    }
}

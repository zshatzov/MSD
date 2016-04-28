package com.avocent.dsview.net.snmp;

/**
 * Created by zshatzov on 4/25/2016.
 */
public class SnmpSetV3RequestBinding extends SnmpSetRequestBinding{

    private String engineID;
    private UserSecurityModel userSecurityModel;


    /**
     * @param clientId A custom ID to correlate requests to responses
     * @param host A hostname or IP address of the remote <em>SNMP</em> agent
     * @param variableBinding An object that encapsulates the parameters to pass to the remote <em>SNMP</em>
     *                        agent to set a new value for a particular MIB variable
     */
    public SnmpSetV3RequestBinding(Integer clientId, String host, SnmpInputVariableBinding variableBinding) {
        super(clientId, host, variableBinding);
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    public UserSecurityModel getUserSecurityModel() {
        return userSecurityModel;
    }

    public void setUserSecurityModel(UserSecurityModel userSecurityModel) {
        this.userSecurityModel = userSecurityModel;
    }
}

package com.avocent.dsview.net.snmp;

/**
 * Created by zshatzov on 4/25/2016.
 */
public class SnmpSetV3RequestBinding extends SnmpSetRequestBinding{

    private String engineID;
    private UserSecurityModel userSecurityModel;


    /**
     * @param clientId
     * @param host
     * @param variableBinding
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

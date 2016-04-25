package com.avocent.dsview.net.snmp;

/**
 * <p>
 * An object that encapsulates all the necessary info to configure the payload
 * of a SNMPv3 GET request. This class encapsulates the authentication and privacy
 * policies in the property {@link UserSecurityModel}. The engineID
 * </p>
 *
 * Created by zshatzov on 4/19/2016.
 */
public final class SnmpGetV3RequestBinding extends SnmpGetRequestBinding {

    private UserSecurityModel userSecurityModel;
    private String engineID;

    /**
     *
     * @param clientId
     * @param host
     * @param oid
     */

    public SnmpGetV3RequestBinding(Integer clientId, String host, String oid) {
        super(clientId, host, oid);
    }

    /**
     *
     * @return String The local engine ID
     */
    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    /**
     *
     * @return UserSecurityModel The USM used to authenticate and encrypt transmissions between the NMS and the agent.
     */
    public UserSecurityModel getUserSecurityModel() {
        return userSecurityModel;
    }

    public void setUserSecurityModel(UserSecurityModel userSecurityModel) {
        this.userSecurityModel = userSecurityModel;
    }
}

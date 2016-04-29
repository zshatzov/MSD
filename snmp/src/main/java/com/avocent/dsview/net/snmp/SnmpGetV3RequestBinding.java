package com.avocent.dsview.net.snmp;

/**
 * <p>
 * An object that encapsulates all the necessary info to configure the payload
 * of a SNMPv3 GET request. This class encapsulates the authentication and privacy
 * policies in the property {@link UserSecurityModel}.
 * </p>
 *
 * Created by zshatzov on 4/19/2016.
 */
public final class SnmpGetV3RequestBinding extends SnmpGetRequestBinding {

    private UserSecurityModel userSecurityModel;
    private String engineID;

    /**
     *
     * @param clientId A custom ID to correlate requests to responses
     * @param host A hostname or IP address of the remote <em>SNMP</em> agent
     * @param oid The MIB OID of a variable binding
     */
    public SnmpGetV3RequestBinding(final Integer clientId, final String host, String oid) {
        super(clientId, host, oid);
    }

    /**
     * <p>
     *  A copy constructor that allows a client to reuse all properties of the
     *  original instance but override the OID
     * </p>
     *
     * @param original An instance to use as template for the new instance
     * @param newOID Override the origianl OID value with new value
     */
    public SnmpGetV3RequestBinding(final SnmpGetV3RequestBinding original, final String newOID){
        this(original.getClientID(), original.getHost(), original.getOid());
        this.oid = newOID;
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

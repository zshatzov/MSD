package com.avocent.dsview.net.snmp;

import java.io.Serializable;

/**
 * <p>
 * An object that encapsulates all the necessary info to configure the payload
 * of either a SNMPv1 or SNMPv3 request.
 * </p>
 *
 * Created by zshatzov on 4/19/2016.
 */
public final class SnmpRequestBinding implements Serializable{

    private final Integer clientId;
    private final String host;
    private final String oid;
    private final String communityString;

    private String engineID;
    private UserSecurityModel userSecurityModel;

    /**
     *
     * @param clientId
     * @param host
     * @param oid
     * @param communityString
     */

    public SnmpRequestBinding(Integer clientId, String host, String oid,
                              String communityString) {
        this.clientId = clientId;
        this.host = host;
        this.oid = oid;
        this.communityString = communityString;
    }

    /**
     * @return An ID setup by the client to correlate request with responses.
     *
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     *
     * @return A hostname or an IP address of the agent.
     */
    public String getHost() {
        return host;
    }

    /**
     *
     * @return The OID of a variable binding
     */
    public String getOid() {
        return oid;
    }

    /**
     *
     * @return The community string associated with the variable binding.
     */
    public String getCommunityString() {
        return communityString;
    }

    public UserSecurityModel getUserSecurityModel() {
        return userSecurityModel;
    }

    /**
     *
     * @param userSecurityModel USM user providing information to secure SNMPv3 message exchange
     */
    public void setUserSecurityModel(UserSecurityModel userSecurityModel) {
        this.userSecurityModel = userSecurityModel;
    }

    public String getEngineID() {
        return engineID;
    }
    /**
     *
     * @param engineID The engine ID for which the user has been localized
     */
    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SnmpRequestBinding(");
        sb.append("host='").append(host).append('\'');
        sb.append(", oid='").append(oid).append('\'');
        sb.append(", communityString='").append(communityString).append('\'');
        sb.append(", clientId=").append(clientId);
        sb.append(')');
        return sb.toString();
    }
}

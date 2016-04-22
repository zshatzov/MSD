package com.avocent.dsview.net.snmp;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * An object that encapsulates all the necessary info to configure the payload
 * of either a SNMPv1 or SNMPv3 SET request.
 * </p>
 *
 * Created by zshatzov on 4/19/2016.
 */
public final class SnmpSetRequestBinding implements Serializable{

    private final Integer clientId;
    private final String host;
    private final SnmpSetVariableBinding variableBinding;


    private String engineID;
    private UserSecurityModel userSecurityModel;


    /**
     *
     * @param clientId
     * @param host
     * @param variableBinding
     */

    public SnmpSetRequestBinding(Integer clientId, String host, SnmpSetVariableBinding variableBinding) {
        this.clientId = clientId;
        this.host = host;
        this.variableBinding = variableBinding;
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
     * @return The new value for a MIB variable
     */
    public SnmpSetVariableBinding getVariableBinding() {
        return variableBinding;
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
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("(");
        sb.append("host='").append(host).append('\'');
        sb.append("oid='").append(variableBinding.getOid()).append('\'');
        sb.append("value='").append(variableBinding.getValue()).append('\'');
        sb.append("type='").append(variableBinding.getVariableType()).append('\'');
        sb.append(", clientId=").append(clientId);
        sb.append(')');
        return sb.toString();
    }
}

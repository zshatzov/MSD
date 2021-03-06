package com.avocent.dsview.net.snmp;

/**
 * <p>
 * An object that encapsulates all the necessary info to configure the payload
 * of either a SNMPv1 or SNMPv3 SET request.
 * </p>
 *
 * Created by zshatzov on 4/19/2016.
 */
public abstract class SnmpSetRequestBinding extends SnmpRequestBinding{

    private final SnmpInputVariableBinding variableBinding;

    /**
     * @param clientID A custom ID to correlate requests to responses
     * @param host A hostname or IP address
     * @param variableBinding An object that encapsulates the new value and MIB OID of a variable binding
     *
     */

    public SnmpSetRequestBinding(Integer clientID, String host, SnmpInputVariableBinding variableBinding) {
        super(clientID, host);
        this.variableBinding = variableBinding;
    }

    /**
     *
     * @return The new value for a MIB variable
     */
    public SnmpInputVariableBinding getVariableBinding() {
        return variableBinding;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("(");
        sb.append("host='").append(getHost()).append('\'');
        sb.append("oid='").append(variableBinding.getOid()).append('\'');
        sb.append("value='").append(variableBinding.getValue()).append('\'');
        sb.append("type='").append(variableBinding.getVariableType()).append('\'');
        sb.append(", clientID=").append(getClientID());
        sb.append(')');
        return sb.toString();
    }
}

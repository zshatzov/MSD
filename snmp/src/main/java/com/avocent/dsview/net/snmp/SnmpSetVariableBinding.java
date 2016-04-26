package com.avocent.dsview.net.snmp;

/**
 * <p>
 * This class represents the variable binding in response to both SNMPv1 and SNMPv3 SET variable binding
 * request. The properties encapsulates the variable type (as an Enum  {@link VariableType}) as well as the
 * new value for the particular variable (designated by the MIB OID).
 * </p>
 *
 * Created by zshatzov on 4/22/16.
 */
public class SnmpSetVariableBinding {

    public enum  VariableType{
        Counter32, Counter64, Gauge32, Integer32, IpAddress, OctetString, OID, Opaque,
        SshAddress, TcpAddress, TimeTicks, TlsAddress, UdpAddress, UnsignedInteger32
    }

    private final String oid;
    private final String value;
    private final VariableType variableType;

    public SnmpSetVariableBinding(String oid, String value, VariableType variableType) {
        this.oid = oid;
        this.value = value;
        this.variableType = variableType;
    }

    public String getOid() {
        return oid;
    }

    public String getValue() {
        return value;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("(");
        sb.append("oid='").append(oid).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", variableType=").append(variableType);
        sb.append(')');
        return sb.toString();
    }
}

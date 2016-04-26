package com.avocent.dsview.net.snmp;

/**
 * <p>
 * An implementation of a variable binding for both SNMPv1 and SNMPv3 SET variable binding
 * request. The properties include the variable type (as an Enum  {@link VariableType}), the new
 * variable value and the corresponding OID of the MIB variable.
 * </p>
 *
 * Created by zshatzov on 4/22/16.
 */
public class SnmpSetVariableBinding extends SnmpVariableBinding {

    public enum  VariableType{
        Counter32, Counter64, Gauge32, Integer32, IpAddress, OctetString, OID, Opaque,
        SshAddress, TcpAddress, TimeTicks, TlsAddress, UdpAddress, UnsignedInteger32
    }

    private final VariableType variableType;

    public SnmpSetVariableBinding(String oid, String value, VariableType variableType) {
        super(oid, value);
        this.variableType = variableType;
    }

    public VariableType getVariableType() {
        return variableType;
    }
}

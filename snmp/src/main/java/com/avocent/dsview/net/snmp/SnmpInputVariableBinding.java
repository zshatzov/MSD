package com.avocent.dsview.net.snmp;

/**
 * <p>
 * Represents a variable binding passed by a <em>Network Management Station</em> to be processed
 * by a remote SNMP agent. The binding includes the new value to set on a MIB variable hosted
 * by the remote SNMP agent.
 * </p>
 *
 * Created by zshatzov on 4/22/16.
 */
public class SnmpInputVariableBinding extends SnmpVariableBinding {

    public enum  VariableType{
        Counter32, Counter64, Gauge32, Integer32, IpAddress, OctetString, OID, Opaque,
        SshAddress, TcpAddress, TimeTicks, TlsAddress, UdpAddress, UnsignedInteger32
    }

    private final VariableType variableType;

    public SnmpInputVariableBinding(String oid, String value, VariableType variableType) {
        super(oid, value);
        this.variableType = variableType;
    }

    public VariableType getVariableType() {
        return variableType;
    }
}

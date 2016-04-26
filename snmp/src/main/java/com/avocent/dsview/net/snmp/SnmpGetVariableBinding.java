package com.avocent.dsview.net.snmp;

/**
 * <p>
 * An implementation of a variable binding for both SNMPv1 and SNMPv3 GET variable binding
 * request. The properties include the variable type as a textual String, the variable value
 * and the corresponding OID of the MIB variable.
 * </p>
 *
 * Created by zshatzov on 4/19/2016.
 */
public final class SnmpGetVariableBinding extends SnmpVariableBinding {

    private final String variableTypeTextual;

    public SnmpGetVariableBinding(String oid, String value, String variableType) {
        super(oid, value);
        this.variableTypeTextual = variableType;
    }

    /**
     *
     * @return The textual type of the value property
     */
    public String getVariableTypeTextual() {
        return variableTypeTextual;
    }
}

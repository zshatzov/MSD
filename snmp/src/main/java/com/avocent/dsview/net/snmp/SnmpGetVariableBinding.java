package com.avocent.dsview.net.snmp;

import java.io.Serializable;

/**
 * <p>
 * This class represents the variable binding in response to both SNMPv1 and SNMPv3 GET/SET
 * requests. The properties encapsulates the variable type (as a textual string) as well as the
 * value of that particular variable (designated by the MIB OID).
 * </p>
 *
 *
 * Created by zshatzov on 4/19/2016.
 */
public final class SnmpGetVariableBinding implements Serializable{

    private final String oid;
    private final String value;
    private final String variableTypeTextual;

    public SnmpGetVariableBinding(String oid, String value, String variableType) {
        this.oid = oid;
        this.value = value;
        this.variableTypeTextual = variableType;
    }

    /**
     *
     * @return The OID of the MIB variable
     */
    public String getOid() {
        return oid;
    }

    /**
     *
     * @return The MIB variable value as a {@link String}
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @return The textual type of the value property
     */
    public String getVariableTypeTextual() {
        return variableTypeTextual;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("(");
        sb.append("oid='").append(oid).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", type='").append(variableTypeTextual).append('\'');
        sb.append(')');
        return sb.toString();
    }
}

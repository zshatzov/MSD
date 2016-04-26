package com.avocent.dsview.net.snmp;

import java.io.Serializable;

/**
 * <p>
 * A base class that encapsulates a SNMP variable binding as a MIB OID and a value properties
 * </p>
 *
 * Created by zshatzov on 4/26/2016.
 */
abstract public class SnmpVariableBinding implements Serializable{

    private final String oid;
    private final String value;

    public SnmpVariableBinding(String oid, String value) {
        this.oid = oid;
        this.value = value;
    }

    public String getOid() {
        return oid;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("(");
        sb.append("oid='").append(oid).append('\'');
        sb.append(", value='").append(value);
        sb.append(')');
        return sb.toString();
    }
}

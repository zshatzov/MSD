package com.avocent.dsview.net.snmp;

import java.io.Serializable;

/**
 * Created by zshatzov on 4/19/2016.
 */
public final class SnmpVariableBinding implements Serializable{

    public enum  VariableType{
        Counter("Counter"),
        ObjectIdentifier("OBJECT IDENTIFIER"),
        OctetString("OCTET STRING"),
        TimeTicks("TimeTicks");

        private final String name;

        private VariableType(String name){
            this.name = name;
        }
    }

    private final String oid;
    private final String value;
    private final String variableType;

    public SnmpVariableBinding(String oid, String value, String variableType) {
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

    public String getVariableType() {
        return variableType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SnmpVariableBinding(");
        sb.append("oid='").append(oid).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", type='").append(variableType).append('\'');
        sb.append(')');
        return sb.toString();
    }
}

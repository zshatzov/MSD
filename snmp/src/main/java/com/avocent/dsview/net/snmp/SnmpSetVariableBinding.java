package com.avocent.dsview.net.snmp;

/**
 * Created by zshatzov on 4/22/16.
 */
public class SnmpSetVariableBinding {

    public enum  VariableType{
        Address, BitString, Counter32, Counter64, Gauge32, Integer32, Null, OctetString, OID, Opaque,
        TimeTicks, TsmSecurityParameters, UnsignedInteger32
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

    public boolean canBeCastToInteger(){
        switch (variableType){
            case Counter32:
            case Gauge32:
            case Integer32:
            case TimeTicks:
            case UnsignedInteger32:
                return true;
            default:
                return false;
        }
    }

    public boolean canBeCastToLong(){
        switch (variableType){
            case Counter64:
                return true;
            default:
                return false;
        }
    }

    public boolean canBeCastToString(){
        switch (variableType){
            case Address:
            case BitString:
            case OctetString:
            case OID:
            case Opaque:
            case TimeTicks:
            case TsmSecurityParameters:
                return true;
            default:
                return false;
        }
    }

    public boolean isNull(){
        return variableType == VariableType.Null;
    }

    public boolean isTimeTicks(){
        return variableType == VariableType.TimeTicks;
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

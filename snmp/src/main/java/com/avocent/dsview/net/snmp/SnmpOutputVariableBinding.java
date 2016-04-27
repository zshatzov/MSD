package com.avocent.dsview.net.snmp;

/**
 * <p>
 * Represents a variable binding returned by a particular SNMP agent as a response to
 * a SNMP request by a <em>Network Management Station</em>. The binding contains
 * current value of the variable hosted on the agent and the type of the variable
 * as a textual string.
 * </p>
 *
 * Created by zshatzov on 4/19/2016.
 */
public final class SnmpOutputVariableBinding extends SnmpVariableBinding {

    private final String variableTypeTextual;

    public SnmpOutputVariableBinding(String oid, String value, String variableType) {
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

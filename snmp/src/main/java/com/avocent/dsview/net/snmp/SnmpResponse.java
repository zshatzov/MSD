package com.avocent.dsview.net.snmp;

import static java.util.Collections.unmodifiableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * A base class that encapsulates the GET/SET response returned from a SNMP agent.
 * </p>
 *
 *
 * Created by zshatzov on 4/19/2016.
 */
public abstract class SnmpResponse implements Serializable {

    protected final Integer clientID;
    protected final List<SnmpGetVariableBinding> variableBindings;
    protected final String errorStatusMessage;
    protected final int errorStatusCode;

    public SnmpResponse(Integer clientID, String errorStatusMessage, int errorStatusCode) {
        this.clientID = clientID;
        this.errorStatusMessage = errorStatusMessage;
        this.errorStatusCode = errorStatusCode;
        this.variableBindings = new ArrayList<>();
    }

    /**
     *
     * @return An ID setup by the client to correlate SNMP requests to SNMP responses
     */
    public Integer getClientID() {
        return clientID;
    }

    public void addVariableBinding(String oid, String value, String variableTpe){
        variableBindings.add(new SnmpGetVariableBinding(oid, value, variableTpe));
    }

    public List<SnmpGetVariableBinding> getVariableBinding(){
        return unmodifiableList(variableBindings);
    }

    /**
     *
     * @return An error ID constant as defined by the class <code>com.ireasoning.protocol.snmp.SnmpErrorStatus</code>
     */
    public int getErrorStatusCode() {
        return errorStatusCode;
    }

    /**
     *
     * @return A textual representation of an error message. For successful operations this field would be the value <em>success</em>
     */
    public String getErrorStatusMessage() {
        return errorStatusMessage;
    }
}

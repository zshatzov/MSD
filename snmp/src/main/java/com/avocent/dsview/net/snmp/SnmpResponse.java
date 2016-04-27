package com.avocent.dsview.net.snmp;

import static java.util.Collections.unmodifiableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * A class that encapsulates the GET/SET response returned from a SNMP agent.
 * </p>
 *
 *
 * Created by zshatzov on 4/19/2016.
 */
public class SnmpResponse<T extends SnmpVariableBinding> implements Serializable {

    private final Integer clientID;
    private final List<T> variableBindings;
    private final String errorStatusMessage;
    private final int errorStatusCode;
    private String contextEngineID;

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

    public void addVariableBinding(T variableBinding){
        variableBindings.add(variableBinding);
    }

    public List<T> getVariableBindings(){
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

    /**
     *
     * @return The ID of the local engine
     */
    public String getContextEngineID() {
        return contextEngineID;
    }

    public void setContextEngineID(String contextEngineID) {
        this.contextEngineID = contextEngineID;
    }
}

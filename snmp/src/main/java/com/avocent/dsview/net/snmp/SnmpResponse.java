package com.avocent.dsview.net.snmp;

import static java.util.Collections.unmodifiableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zshatzov on 4/19/2016.
 */
public abstract class SnmpResponse implements Serializable {
    protected final Integer clientId;
    protected final List<SnmpVariableBinding> variableBindings;
    protected final String requestId;
    protected final String errorStatusMessage;
    protected final int errorStatusCode;

    public SnmpResponse(Integer clientId, String requestId,
                        String errorStatusMessage, int errorStatusCode) {
        this.clientId = clientId;
        this.requestId = requestId;
        this.errorStatusMessage = errorStatusMessage;
        this.errorStatusCode = errorStatusCode;
        this.variableBindings = new ArrayList<>();
    }

    public Integer getClientId() {
        return clientId;
    }

    public void addVariableBinding(String oid, String value, String variableTpe){
        variableBindings.add(new SnmpVariableBinding(oid, value, variableTpe));
    }

    public String getRequestId() {
        return requestId;
    }

    public List<SnmpVariableBinding> getVariableBinding(){
        return unmodifiableList(variableBindings);
    }

    public int getErrorStatusCode() {
        return errorStatusCode;
    }

    public String getErrorStatusMessage() {
        return errorStatusMessage;
    }
}

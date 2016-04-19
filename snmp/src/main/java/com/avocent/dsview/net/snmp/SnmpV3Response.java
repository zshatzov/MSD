package com.avocent.dsview.net.snmp;

/**
 * Created by zshatzov on 4/19/2016.
 */
public class SnmpV3Response extends SnmpV1Response {

    private final String contextEngineID;

    public SnmpV3Response(String contextEngineID,
                          Integer clientId, String requestId,
                          String errorStatusMessage, int errorStatusCode) {
        super(clientId, requestId, errorStatusMessage,
                errorStatusCode);
        this.contextEngineID = contextEngineID;
    }

    public String getContextEngineID() {
        return contextEngineID;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

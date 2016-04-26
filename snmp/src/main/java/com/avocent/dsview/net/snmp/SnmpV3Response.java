package com.avocent.dsview.net.snmp;

/**
 * <p>A SNMPv3 implementation of the base class {@link SnmpResponse}</p>
 *
 * Created by zshatzov on 4/19/2016.
 */
public class SnmpV3Response extends SnmpV1Response {

    private final String contextEngineID;

    public SnmpV3Response(String contextEngineID, Integer clientID,
                          String errorStatusMessage, int errorStatusCode) {
        super(clientID, errorStatusMessage, errorStatusCode);
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

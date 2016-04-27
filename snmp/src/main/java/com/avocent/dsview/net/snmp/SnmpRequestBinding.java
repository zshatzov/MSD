package com.avocent.dsview.net.snmp;

import java.io.Serializable;

/**
 * <p>
 *  A base class that encapsulates common properties for both SNMP GET/SET requests.
 * </p>
 *
 * Created by zshatzov on 4/27/2016.
 */
public abstract class SnmpRequestBinding implements Serializable{

    private final Integer clientID;
    private final String host;

    public SnmpRequestBinding(Integer clientID, String host) {
        this.clientID = clientID;
        this.host = host;
    }

    /**
     *
     * @return A client ID that is used to correlate requests to responses
     */
    public Integer getClientID() {
        return clientID;
    }

    /**
     *
     * @return The host (or IP address) of the remote agent
     */
    public String getHost() {
        return host;
    }
}

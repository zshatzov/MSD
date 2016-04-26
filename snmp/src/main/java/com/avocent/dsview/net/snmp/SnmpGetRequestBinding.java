package com.avocent.dsview.net.snmp;

import java.io.Serializable;

/**
 * <p>
 *  A base class for SNMPv1 and SNMPv3 request binding.
 *  This class encapsulates the common properties required for both SNMPv1 and
 *  SNMPv3 GET requests.
 * </p>
 *
 *
 * Created by zshatzov on 4/25/2016.
 */
public abstract class SnmpGetRequestBinding implements Serializable{

    private final Integer clientId;
    private final String host;
    private final String oid;

    public SnmpGetRequestBinding(Integer clientId, String host, String oid) {
        this.clientId = clientId;
        this.host = host;
        this.oid = oid;
    }

    /**
     *
     * @return Integer A client provided ID that can be used to correlate requests to responses.
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     *
     * @return String Either an IP address or a hostname
     */
    public String getHost() {
        return host;
    }

    /**
     *
     * @return String The MIB OID for the variable binding
     */
    public String getOid() {
        return oid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("(");
        sb.append("host='").append(host).append('\'');
        sb.append(", oid='").append(oid).append('\'');
        sb.append(", clientID=").append(clientId);
        sb.append(')');
        return sb.toString();
    }
}

package com.avocent.dsview.net.snmp;

import java.io.Serializable;

/**
 * Created by zshatzov on 4/19/2016.
 */
public final class SnmpRequestBinding implements Serializable{
    private final Integer clientId;
    private final String host;
    private final String oid;
    private final String communityString;

    public SnmpRequestBinding(Integer clientId, String host, String oid,
                              String communityString) {
        this.clientId = clientId;
        this.host = host;
        this.oid = oid;
        this.communityString = communityString;
    }

    public Integer getClientId() {
        return clientId;
    }

    public String getHost() {
        return host;
    }

    public String getOid() {
        return oid;
    }

    public String getCommunityString() {
        return communityString;
    }
}

package com.avocent.dsview.net.snmp;

/**
 * <p>
 * An object that encapsulates all the necessary info to configure the payload
 * of a SNMPv1 GET request.
 * </p>
 *
 * Created by zshatzov on 4/19/2016.
 */
public final class SnmpGetV1RequestBinding extends SnmpGetRequestBinding {

    private final String communityString;
    /**
     *
     * @param clientID A custom ID to correlate requests to responses
     * @param host A hostname or IP address
     * @param oid The MIB OID of a variable binding
     * @param communityString Either <em>private</em> or <em>public</em>
     */

    public SnmpGetV1RequestBinding(Integer clientID, String host, String oid,
                                   String communityString) {
        super(clientID, host, oid);
        this.communityString = communityString;
    }

    /**
     *
     * @return The community string associated with the variable binding.
     */
    public String getCommunityString() {
        return communityString;
    }
}

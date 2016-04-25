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
     * @param clientId
     * @param host
     * @param oid
     * @param communityString
     */

    public SnmpGetV1RequestBinding(Integer clientId, String host, String oid,
                                   String communityString) {
        super(clientId, host, oid);
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

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
     * @param host A hostname or IP address of the remote <em>SNMP</em> agent
     * @param oid The MIB OID of a variable binding
     * @param communityString Either <em>private</em> or <em>public</em>
     */

    public SnmpGetV1RequestBinding(final Integer clientID, final String host, final String oid,
                                   final String communityString) {
        super(clientID, host, oid);
        this.communityString = communityString;
    }

    /**
     * <p>
     *  A copy constructor that allows a client to reuse all properties of the
     *  original instance but override the OID
     * </p>
     *
     * @param original An instance to use as template for the new instance
     * @param newOID Override the origianl OID value with new value
     */
    public SnmpGetV1RequestBinding(final SnmpGetV1RequestBinding original, final String newOID){
        this(original.getClientID(), original.getHost(), original.getOid(),
                original.getCommunityString());
        this.oid = newOID;
    }
    /**
     *
     * @return The community string associated with the variable binding.
     */
    public String getCommunityString() {
        return communityString;
    }
}

package com.avocent.dsview.net.snmp;

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
public abstract class SnmpGetRequestBinding extends SnmpRequestBinding{

    String oid;

    /**
     *
     * @param clientID A custom ID to correlate requests to responses
     * @param host A hostname or IP address
     * @param oid The MIB OID of a variable binding
     */
    public SnmpGetRequestBinding(Integer clientID, String host, String oid) {
        super(clientID, host);
        this.oid = oid;
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
        sb.append("host='").append(getHost()).append('\'');
        sb.append(", oid='").append(oid).append('\'');
        sb.append(", clientID=").append(getClientID());
        sb.append(')');
        return sb.toString();
    }
}

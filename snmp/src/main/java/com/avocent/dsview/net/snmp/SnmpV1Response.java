package com.avocent.dsview.net.snmp;

/**
 * <p>A SNMPv1 implementation of the base class {@link SnmpResponse}</p>
 *
 * Created by zshatzov on 4/19/2016.
 */
public class SnmpV1Response extends SnmpResponse{

    public SnmpV1Response(Integer clientId, String requestId, String errorStatusMessage, int errorStatusCode) {
        super(clientId, requestId, errorStatusMessage, errorStatusCode);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("(");
        sb.append("requestID: " + requestId + ", ");
        sb.append("errorStatusMessage: " + errorStatusMessage + ", ");
        sb.append("errorStatusCode: " + errorStatusCode);
        sb.append(')');
        return sb.toString();
    }
}

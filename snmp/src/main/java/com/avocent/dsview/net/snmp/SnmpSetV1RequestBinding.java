package com.avocent.dsview.net.snmp;

/**
 * Created by zshatzov on 4/25/2016.
 */
public class SnmpSetV1RequestBinding extends SnmpSetRequestBinding{

    private final String communityString;

    /**
     * @param clientId A custom ID to correlate requests to responses
     * @param host A hostname or IP address of the remote <em>SNMP</em> agent
     * @param variableBinding An object that encapsulates the MIB OID and new value for a
     *                        particular MIB variable
     */
    public SnmpSetV1RequestBinding(final Integer clientId, final String host,
                                   final SnmpInputVariableBinding variableBinding,
                                   final String communityString) {
        super(clientId, host, variableBinding);
        this.communityString = communityString;
    }

    public String getCommunityString() {
        return communityString;
    }
}

package com.avocent.dsview.net.snmp;

/**
 * Created by zshatzov on 4/25/2016.
 */
public class SnmpSetV1RequestBinding extends SnmpSetRequestBinding{

    private final String communityString;

    /**
     * @param clientId
     * @param host
     * @param variableBinding
     */
    public SnmpSetV1RequestBinding(Integer clientId, String host,
                                   SnmpSetVariableBinding variableBinding,
                                   String communityString) {
        super(clientId, host, variableBinding);
        this.communityString = communityString;
    }

    public String getCommunityString() {
        return communityString;
    }
}

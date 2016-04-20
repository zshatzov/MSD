package com.avocent.dsview.net.snmp;

import java.io.Serializable;

/**
 * Created by zshatzov on 4/19/2016.
 */
public final class SnmpRequestBinding implements Serializable{

    public enum SecurityLevel{undefined, noAuthNoPriv, authNoPriv, authPriv}

    private final Integer clientId;
    private final String host;
    private final String oid;
    private final String communityString;

    private SecurityLevel securityLevel;
    private String securityName;
    private String authenticationPassphrase;
    private String privacyPassphrase;
    private String authenticationProtocol;
    private String privacyProtocol;
    private String localizationEngineID;

    /**
     *
     * @param clientId
     * @param host
     * @param oid
     * @param communityString
     */

    public SnmpRequestBinding(Integer clientId, String host, String oid,
                              String communityString) {
        this.clientId = clientId;
        this.host = host;
        this.oid = oid;
        this.communityString = communityString;
    }

    /**
     * <p>
     *     An ID setup by the client to correlate request with responses.
     * </p>
     *
     * @return
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * A hostname or an IP address of the agent.
     *
     * @return
     */
    public String getHost() {
        return host;
    }

    /**
     * An OID variable binding
.     *
     * @return
     */
    public String getOid() {
        return oid;
    }

    /**
     * The community string associated with the variable binding.
     *
     * @return
     */
    public String getCommunityString() {
        return communityString;
    }

    /**
     * <p>The authentication passphrase. If not null, authenticationProtocol must also be not null.</p>
     * <p><em>RFC3414 §11.2 requires passphrases to have a minimum length of 8 bytes</em>.</p>
     * <p>If the length of authenticationPassphrase is less than 8 bytes an IllegalArgumentException is thrown.</p>
     * @return
     */
    public String getAuthenticationPassphrase() {
        return authenticationPassphrase;
    }

    public void setAuthenticationPassphrase(String authenticationPassphrase) {
        this.authenticationPassphrase = authenticationPassphrase;
    }

    /**
     * <p>
     * The authentication protcol ID to be associated with this user. If set to null, this user only supports unauthenticated messages.
     * </p>
     * @return
     */
    public String getAuthenticationProtocol() {
        return authenticationProtocol;
    }

    public void setAuthenticationProtocol(String authenticationProtocol) {
        this.authenticationProtocol = authenticationProtocol;
    }

    /**
     * <p>if not null, the localizationEngineID specifies the engine ID for which the supplied passphrases are already localized.</p>
     * <p>Such an USM user can only be used with the target whose engine ID equals localizationEngineID.</p>
     *
     * @return
     */
    public String getLocalizationEngineID() {
        return localizationEngineID;
    }

    public void setLocalizationEngineID(String localizationEngineID) {
        this.localizationEngineID = localizationEngineID;
    }

    public String getPrivacyPassphrase() {
        return privacyPassphrase;
    }

    public void setPrivacyPassphrase(String privacyPassphrase) {
        this.privacyPassphrase = privacyPassphrase;
    }

    /**
     * <p>
     *     The privacy protcol ID to be associated with this user.
     *     If set to null, this user only supports unencrypted messages.
     *  </p>
     * @return
     */
    public String getPrivacyProtocol() {
        return privacyProtocol;
    }

    public void setPrivacyProtocol(String privacyProtocol) {
        this.privacyProtocol = privacyProtocol;
    }

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    /**
     * <p>
     *     The security name of the user (typically the user name)
     * </p>
     *
     * @return
     */
    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SnmpRequestBinding(");
        sb.append("host='").append(host).append('\'');
        sb.append(", oid='").append(oid).append('\'');
        sb.append(", communityString='").append(communityString).append('\'');
        sb.append(", clientId=").append(clientId);
        sb.append(')');
        return sb.toString();
    }
}

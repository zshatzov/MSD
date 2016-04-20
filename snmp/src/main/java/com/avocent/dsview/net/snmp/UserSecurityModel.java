package com.avocent.dsview.net.snmp;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * An object that represents SNMPv3 <em>User Security Model</em> as defined in RFC 3414
 * </p>
 *
 *
 *
 * Created by zshatzov on 4/20/16.
 */
public class UserSecurityModel implements Serializable{

    public enum SecurityLevel{undefined, noAuthNoPriv, authNoPriv, authPriv}

    public UserSecurityModel() {
    }

    private SecurityLevel securityLevel;
    private String securityName;
    private String authenticationPassphrase;
    private String privacyPassphrase;
    private String authenticationProtocol;
    private String privacyProtocol;
    private String localizationEngineID;

    /**
     * A convenience builder method that can be chained with another method
     *
     * @param authenticationProtocol
     * @param authenticationPassphrase
     * @return
     */
    public UserSecurityModel addAuthenticationInfo(String authenticationProtocol, String authenticationPassphrase){
        if(Objects.nonNull(authenticationProtocol)) {
            setAuthenticationProtocol(authenticationProtocol);
        }

        if(Objects.nonNull(authenticationPassphrase)) {
            setAuthenticationPassphrase(authenticationPassphrase);
        }

        return this;
    }

    /**
     * A convenience builder method that can be chained with another method
     *
     * @param privacyProtocol
     * @param privacyPassphrase
     * @return
     */
    public UserSecurityModel addPrivacyInfo(String privacyProtocol, String privacyPassphrase){
        if(Objects.nonNull(privacyProtocol)) {
            setAuthenticationProtocol(authenticationProtocol);
        }

        if(Objects.nonNull(privacyPassphrase)) {
            setAuthenticationPassphrase(authenticationPassphrase);
        }

        return this;
    }

    public String getAuthenticationPassphrase() {
        return authenticationPassphrase;
    }

    /**
     * <p>
     * If not null, authenticationProtocol must also be not null.
     * </p>
     *
     * <p>
     * RFC3414 §11.2 requires passphrases to have a minimum length of 8 bytes.
     * If the length of authenticationPassphrase is less than 8 bytes an IllegalArgumentException is thrown.
     * </p>
     *
     * @param authenticationPassphrase  The authentication passphrase.
     */
    public void setAuthenticationPassphrase(String authenticationPassphrase) {
        this.authenticationPassphrase = authenticationPassphrase;
    }

    public String getAuthenticationProtocol() {
        return authenticationProtocol;
    }

    public void setAuthenticationProtocol(String authenticationProtocol) {
        this.authenticationProtocol = authenticationProtocol;
    }

    public String getLocalizationEngineID() {
        return localizationEngineID;
    }

    public void setLocalizationEngineID(String localizationEngineID) {
        this.localizationEngineID = localizationEngineID;
    }

    public String getPrivacyPassphrase() {
        return privacyPassphrase;
    }

    /**
     * <p>
     *   If not null, privacyProtocol must also be not null.
     * </p>
     *
     * <p>
     *    RFC3414 §11.2 requires passphrases to have a minimum length of 8 bytes.
     *    If the length of authenticationPassphrase is less than 8 bytes an IllegalArgumentException is thrown.
     * </p>
     *
     * @param privacyPassphrase  The privacy passphrase
     */
    public void setPrivacyPassphrase(String privacyPassphrase) {
        this.privacyPassphrase = privacyPassphrase;
    }

    public String getPrivacyProtocol() {
        return privacyProtocol;
    }

    /**
     * <p> If set to null, this user only supports unencrypted messages.</p>
     *
     * @param privacyProtocol The privacy protocol ID to be associated with this user
     */
    public void setPrivacyProtocol(String privacyProtocol) {
        this.privacyProtocol = privacyProtocol;
    }

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserSecurityModel(");
        sb.append("authenticationPassphrase='").append(authenticationPassphrase).append('\'');
        sb.append(", securityLevel=").append(securityLevel);
        sb.append(", securityName='").append(securityName).append('\'');
        sb.append(", privacyPassphrase='").append(privacyPassphrase).append('\'');
        sb.append(", authenticationProtocol='").append(authenticationProtocol).append('\'');
        sb.append(", privacyProtocol='").append(privacyProtocol).append('\'');
        sb.append(", localizationEngineID='").append(localizationEngineID).append('\'');
        sb.append(')');
        return sb.toString();
    }
}
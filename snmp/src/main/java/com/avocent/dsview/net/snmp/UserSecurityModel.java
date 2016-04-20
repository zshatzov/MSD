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
    private String userName;
    private String securityName;
    private String authenticationPassphrase;
    private String privacyPassphrase;
    private String authenticationProtocol;
    private String privacyProtocol;

    /**
     * A convenience builder method that can be chained with another method.
     *
     * @param userName The user name
     * @param securityName The security name (typically same as the user name). If null default to userName
     * @param securityLevel An enum that defines authentication and privacy. If null default to SecurityLevel.undefined.
     *
     * @return
     */
    public UserSecurityModel addUserInfo(String userName, String securityName, SecurityLevel securityLevel){
        if(Objects.nonNull(userName)){
            setUserName(userName);
        }

        if(Objects.nonNull(securityName)){
            setSecurityName(securityName);
        }else{
            setSecurityName(userName);
        }

        if(Objects.nonNull(securityLevel)){
            setSecurityLevel(securityLevel);
        }else{
            setSecurityLevel(SecurityLevel.undefined);
        }

        return this;
    }


    /**
     * A convenience builder method that can be chained with another method
     *
     * @param authenticationProtocol  The authentication protocol ID to be associated with this user. If set to null, this user only supports unauthenticated messages.
     * @param authenticationPassphrase The authentication passphrase. If not null, authenticationProtocol must also be not null.
     *
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
     * @param privacyProtocol  The privacy protocol ID to be associated with this user. If set to null, this user only supports unencrypted messages.
     * @param privacyPassphrase The privacy passphrase. If not null, privacyProtocol must also be not null
     *
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
    private void setAuthenticationPassphrase(String authenticationPassphrase) {
        this.authenticationPassphrase = authenticationPassphrase;
    }

    public String getAuthenticationProtocol() {
        return authenticationProtocol;
    }

    private void setAuthenticationProtocol(String authenticationProtocol) {
        this.authenticationProtocol = authenticationProtocol;
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
    private void setPrivacyPassphrase(String privacyPassphrase) {
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
    private void setPrivacyProtocol(String privacyProtocol) {
        this.privacyProtocol = privacyProtocol;
    }

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    private void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getSecurityName() {
        return securityName;
    }

    /**
     *
     * @param securityName The security name of the user (typically the user name)
     */
    private void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName A user name
     */
    private void setUserName(String userName) {
        this.userName = userName;
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
        sb.append(')');
        return sb.toString();
    }
}
package com.avocent.dsview.net.snmp;

import java.io.Serializable;
import java.util.*;

import static java.util.Objects.*;

/**
 * <p>
 * An object that represents SNMPv3 <em>User Security Model</em> as defined in RFC 3414
 * </p>
 *
 *
 *
 * Created by zshatzov on 4/20/16.
 */
public final class UserSecurityModel implements Serializable{

    public enum SecurityLevel{undefined, noAuthNoPriv, authNoPriv, authPriv}

    public enum AuthProtocol{

        MD5("MD5", "1.3.6.1.6.3.10.1.1.2"),
        SHA("SHA", "1.3.6.1.6.3.10.1.1.3");

        private String value;
        private String ID;

        private AuthProtocol(String value, String ID){
            this.value = value;
            this.ID = ID;
        }

        public String getValue(){
            return this.value;
        }

        public String getID(){
            return this.ID;
        }

        public List<Entry> entries(){
            final List<Entry> entries = new ArrayList<>();
            for(AuthProtocol protocol: values()){
                    Entry entry = new Entry(protocol.getValue(), protocol.ID);
                    entries.add(entry);
            }

            return entries;
        }

        public static class Entry{
            private final String key;
            private final String value;

            public Entry(String key, String value) {
                this.key = key;
                this.value = value;
            }

            public String getKey(){
                return key;
            }

            public String getValue(){
                return value;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Entry entry = (Entry) o;
                return Objects.equals(key, entry.key) &&
                        Objects.equals(value, entry.value);
            }

            @Override
            public int hashCode() {
                return Objects.hash(key, value);
            }
        }
    }

    public enum PrivProtocol{

        DES("DES", "1.3.6.1.6.3.10.1.2.2");

        private String value;
        private String ID;

        private PrivProtocol(String value, String ID){
            this.value = value;
            this.ID = ID;
        }

        public String getValue(){
            return this.value;
        }

        public String getID(){
            return this.ID;
        }
    }

    private UserSecurityModel() {
    }

    private SecurityLevel securityLevel;
    private String userName;
    private String securityName;
    private String authenticationPassphrase;
    private String privacyPassphrase;
    private AuthProtocol authenticationProtocol;
    private PrivProtocol privacyProtocol;


    public static Builder builder(){
        return new Builder();
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

    public AuthProtocol getAuthenticationProtocol() {
        return authenticationProtocol;
    }

    private void setAuthenticationProtocol(AuthProtocol authenticationProtocol) {
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

    public PrivProtocol getPrivacyProtocol() {
        return privacyProtocol;
    }

    /**
     * <p> If set to null, this user only supports unencrypted messages.</p>
     *
     * @param privacyProtocol The privacy protocol ID to be associated with this user
     */
    private void setPrivacyProtocol(PrivProtocol privacyProtocol) {
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

    /**
     * An implementation of a builder helper class to allow
     * user a seamless construction of the {@link UserSecurityModel} instances.
     *
     * @author zshatzov
     */
    public static final class Builder{

        private UserSecurityModel usm;

        public Builder(){
            usm = new UserSecurityModel();
        }


        public UserSecurityModel build(){
            return usm;
        }

        /**
         * A convenience builder method that can be chained with another method.
         *
         * @param userName The user name
         * @param securityName The security name (typically same as the user name). If null default to userName
         * @param securityLevel An enum that defines authentication and privacy. If null default to SecurityLevel.undefined.
         *
         * @return
         */
        public Builder addUserInfo(String userName, String securityName, SecurityLevel securityLevel){
            if(nonNull(userName)){
                usm.setUserName(userName);
            }

            if(nonNull(securityName)){
                usm.setSecurityName(securityName);
            }else{
                usm.setSecurityName(userName);
            }

            if(nonNull(securityLevel)){
                usm.setSecurityLevel(securityLevel);
            }else{
                usm.setSecurityLevel(SecurityLevel.undefined);
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
        public Builder addAuthenticationInfo(AuthProtocol authenticationProtocol, String authenticationPassphrase){
            if(nonNull(authenticationProtocol)) {
                usm.setAuthenticationProtocol(authenticationProtocol);
            }

            if(nonNull(authenticationPassphrase)) {
                usm.setAuthenticationPassphrase(authenticationPassphrase);
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
        public Builder addPrivacyInfo(PrivProtocol privacyProtocol, String privacyPassphrase){

            if(nonNull(privacyProtocol)) {
                usm.setPrivacyProtocol(privacyProtocol);
            }

            if(nonNull(privacyPassphrase)) {
                usm.setPrivacyPassphrase(privacyPassphrase);
            }

            return this;
        }
    }
}
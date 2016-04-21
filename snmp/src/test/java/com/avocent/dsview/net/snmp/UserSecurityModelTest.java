package com.avocent.dsview.net.snmp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by zshatzov on 4/21/16.
 */

public class UserSecurityModelTest {

    private UserSecurityModel.Builder builder;

    @Before
    public void setup() throws Exception{
        builder = UserSecurityModel.builder();
    }


    @Test
    public void addPrivacyInfo() throws Exception{
        UserSecurityModel usm = builder.addPrivacyInfo(UserSecurityModel.PrivProtocol.AES,
                "helloworld").build();

        assertEquals(usm.getPrivacyProtocol(), UserSecurityModel.PrivProtocol.AES);
        assertEquals(usm.getPrivacyPassphrase(), "helloworld");
    }

    @Test
    public void addAuthenticationInfo() throws Exception{
        UserSecurityModel usm = builder.addAuthenticationInfo(UserSecurityModel.AuthProtocol.MD5, "bighead").build();

        assertEquals(usm.getAuthenticationProtocol(), UserSecurityModel.AuthProtocol.MD5);
        assertEquals(usm.getAuthenticationPassphrase(), "bighead");
    }

    @Test
    public void addUserInfo() throws Exception{
        UserSecurityModel usm = builder.addUserInfo("admin", "adminsafe", UserSecurityModel.SecurityLevel.authPriv).build();

        assertEquals(usm.getUserName(), "admin");
        assertEquals(usm.getSecurityName(), "adminsafe");
        assertEquals(usm.getSecurityLevel(), UserSecurityModel.SecurityLevel.authPriv);
    }


    @Test
    public void addAll() throws Exception{
        UserSecurityModel usm =
                builder.addUserInfo("admin", "adminsafe", UserSecurityModel.SecurityLevel.authPriv)
                       .addPrivacyInfo(UserSecurityModel.PrivProtocol.AES, "helloworld")
                       .addAuthenticationInfo(UserSecurityModel.AuthProtocol.MD5, "bighead")
                       .build();
        assertEquals(usm.getUserName(), "admin");
        assertEquals(usm.getSecurityName(), "adminsafe");
        assertEquals(usm.getSecurityLevel(), UserSecurityModel.SecurityLevel.authPriv);
        assertEquals(usm.getPrivacyProtocol(), UserSecurityModel.PrivProtocol.AES);
        assertEquals(usm.getPrivacyPassphrase(), "helloworld");
        assertEquals(usm.getAuthenticationProtocol(), UserSecurityModel.AuthProtocol.MD5);
        assertEquals(usm.getAuthenticationPassphrase(), "bighead");
    }


    @After
    public void cleanup(){
        builder = null;
    }
}

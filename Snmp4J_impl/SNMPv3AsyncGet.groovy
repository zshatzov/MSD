import com.avocent.dsview.net.snmp.SnmpEventListener
import com.avocent.dsview.net.snmp.SnmpGetV3RequestBinding
import com.avocent.dsview.net.snmp.SnmpResponse
import com.avocent.dsview.net.snmp.UserSecurityModel
import com.avocent.dsview.net.snmp.impl.Snmp4JV3Service

import java.util.stream.Stream

usm = UserSecurityModel.builder()
        .addAuthenticationInfo(UserSecurityModel.AuthProtocol.MD5, 'authkey1')
        .addPrivacyInfo(UserSecurityModel.PrivProtocol.DES, 'privkey1')
        .addGeneralSecurityInfo('usr-md5-des', 'usr-md5-des', UserSecurityModel.SecurityLevel.authPriv)
        .build()

req1 = new SnmpGetV3RequestBinding(1, 'demo.snmplabs.com', '1.3.6.1.2.1.1.3.0')
req1.userSecurityModel = usm

req2 = new SnmpGetV3RequestBinding(2, 'demo.snmplabs.com', '1.3.6.1.2.1.1.4.0')
req2.userSecurityModel = usm

callback = { Stream<SnmpResponse> stream->
    stream.forEach{response->
        println "\n${'=>' * 50}\n"
        response.with{
            println "ClientID: $clientID"
            println "Context Engine ID: $contextEngineID"
            println "Error Messge: $errorStatusMessage"
            println "Error Code: $errorStatusCode"
            variableBindings.each{
                println it.oid
                println it.value
                println it.variableTypeTextual
            }
        }
    }
} as SnmpEventListener


snmp = new Snmp4JV3Service()
snmp.get(callback, [req1, req2].stream())
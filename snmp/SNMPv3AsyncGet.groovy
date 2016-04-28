import com.avocent.dsview.net.snmp.*
import com.avocent.dsview.net.snmp.impl.*
import java.util.stream.Stream

import org.snmp4j.security.*

usm = UserSecurityModel.builder()
        .addAuthenticationInfo(UserSecurityModel.AuthProtocol.MD5, 'authkey1')
        .addPrivacyInfo(UserSecurityModel.PrivProtocol.DES, 'privkey1')
        .addGeneralSecurityInfo('usr-md5-des', 'usr-md5-des', UserSecurityModel.SecurityLevel.authPriv)
        .build()

req1 = new SnmpGetV3RequestBinding(1, 'demo.snmplabs.com', '1.3.6.1.2.1.1.3.0')
req1.userSecurityModel = usm

req2 = new SnmpGetV3RequestBinding(2, 'demo.snmplabs.com', '1.3.6.1.2.1.1.4.0')
req2.userSecurityModel = usm

nms = new Snmp4JV3Service()
nms.get(
        {Stream<SnmpResponse> responses-> responses.forEach{
            println "\n${'=>' * 50}\n"
            it.with{
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
        }}, [req1, req2].stream())
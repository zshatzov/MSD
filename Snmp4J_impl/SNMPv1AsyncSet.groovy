import com.avocent.dsview.net.snmp.SnmpEventListener
import com.avocent.dsview.net.snmp.SnmpInputVariableBinding
import com.avocent.dsview.net.snmp.SnmpResponse
import com.avocent.dsview.net.snmp.SnmpSetV1RequestBinding
import com.avocent.dsview.net.snmp.impl.Snmp4JV1Service

import java.util.stream.Stream

vb = new SnmpInputVariableBinding('1.3.6.1.2.1.1.9.1.3.1', 'Hello World', SnmpInputVariableBinding.VariableType.OctetString)
request = new SnmpSetV1RequestBinding(1, 'demo.snmplabs.com', vb, 'private')
snmp = new Snmp4JV1Service()
callback = { Stream<SnmpResponse> stream ->
    stream.forEach{response->
        response.with{
            println clientID
            println errorStatusMessage
            println errorStatusCode
            variableBindings.each{
                println it.oid
                println it.value
                println it.variableTypeTextual
            }
        }
    }
} as SnmpEventListener

snmp.set(callback, [request].stream())
import com.avocent.dsview.net.snmp.*
import com.avocent.dsview.net.snmp.impl.*


vb = new SnmpSetVariableBinding('1.3.6.1.2.1.1.9.1.3.1', 'Hello World', SnmpSetVariableBinding.VariableType.OctetString)
request = new SnmpSetV1RequestBinding(1, 'demo.snmplabs.com', vb, 'private')
nms = new Snmp4JNetworkManagementStation()
response = nms.setSnmpV1Async({it.forEach{response->
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
}, [request].stream())
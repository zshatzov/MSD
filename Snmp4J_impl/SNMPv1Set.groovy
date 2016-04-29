import com.avocent.dsview.net.snmp.SnmpInputVariableBinding
import com.avocent.dsview.net.snmp.SnmpSetV1RequestBinding
import com.avocent.dsview.net.snmp.impl.Snmp4JV1Service


vb = new SnmpInputVariableBinding('1.3.6.1.2.1.1.9.1.3.1', 'Hello World', SnmpInputVariableBinding.VariableType.OctetString)
request = new SnmpSetV1RequestBinding(1, 'demo.snmplabs.com', vb, 'private')
snmp = new Snmp4JV1Service()
response = snmp.set(request)

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
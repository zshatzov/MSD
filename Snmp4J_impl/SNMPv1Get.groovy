import com.avocent.dsview.net.snmp.SnmpGetV1RequestBinding
import com.avocent.dsview.net.snmp.impl.Snmp4JV1Service


request = new SnmpGetV1RequestBinding(1, '66.214.208.104', '1.3.6.1.2.1.1.9.1.3.1', 'private')
snmp = new Snmp4JV1Service()
response = snmp.get(request)

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
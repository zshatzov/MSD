import com.avocent.dsview.net.snmp.*
import com.avocent.dsview.net.snmp.impl.*


request = new SnmpGetV1RequestBinding(1, '66.214.208.104', '1.3.6.1.2.1.1.9.1.3.1', 'private')
nms = new Snmp4JV1Operations()
response = nms.get(request)

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
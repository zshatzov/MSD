package com.avocent.dsview.net.snmp;

import java.util.stream.Stream;

/**
 * <p>An interface that represents a <em>SNMP Network Management Station</em>. This interface
 * provides both synchronous and asynchronous GET operations on a SNMP agent.</p>
 *
 *
 * Created by zshatzov on 4/19/2016.
 */
public interface NetworkManagementStation {

    SnmpV1Response getSnmpV1(SnmpGetV1RequestBinding binding);

    SnmpV3Response getSnmpV3(SnmpGetV3RequestBinding binding);

    void getSnmpV1Async(SnmpGetEventListener<SnmpV1Response> listener, Stream<SnmpGetV1RequestBinding> bindings);

    void getSnmpV3Async(SnmpGetEventListener<SnmpV3Response> listener, Stream<SnmpGetV3RequestBinding> bindings);

    SnmpV1Response setSnmpV1(SnmpSetRequestBinding binding);

    SnmpV3Response setSnmpV3(SnmpSetRequestBinding binding);
}

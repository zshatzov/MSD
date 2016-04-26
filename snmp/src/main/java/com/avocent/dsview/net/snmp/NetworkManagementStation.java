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

    SnmpV1Response getSnmpV1(SnmpGetV1RequestBinding requestBinding);

    SnmpV3Response getSnmpV3(SnmpGetV3RequestBinding requestBinding);

    void getSnmpV1Async(SnmpGetEventListener<SnmpV1Response> callback,
                        Stream<SnmpGetV1RequestBinding> requestBindings);

    void getSnmpV3Async(SnmpGetEventListener<SnmpV3Response> callback,
                        Stream<SnmpGetV3RequestBinding> requestBindings);

    SnmpV1Response setSnmpV1(SnmpSetV1RequestBinding requestBinding);

    SnmpV3Response setSnmpV3(SnmpSetV3RequestBinding requestBinding);
}

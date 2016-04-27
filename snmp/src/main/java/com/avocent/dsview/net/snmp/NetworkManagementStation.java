package com.avocent.dsview.net.snmp;

import java.util.stream.Stream;

/**
 * <p>An interface that represents a <em>SNMP Network Management Station</em>. This interface
 * provides both synchronous and asynchronous SNMP GET/SET operations.</p>
 *
 *
 * Created by zshatzov on 4/19/2016.
 */
public interface NetworkManagementStation {

    SnmpResponse getSnmpV1(SnmpGetV1RequestBinding requestBinding);

    SnmpResponse getSnmpV3(SnmpGetV3RequestBinding requestBinding);

    void getSnmpV1Async(SnmpGetEventListener<SnmpResponse> callback,
                        Stream<SnmpGetV1RequestBinding> requestBindings);

    void getSnmpV3Async(SnmpGetEventListener<SnmpResponse> callback,
                        Stream<SnmpGetV3RequestBinding> requestBindings);

    SnmpResponse setSnmpV1(SnmpSetV1RequestBinding requestBinding);

    SnmpResponse setSnmpV3(SnmpSetV3RequestBinding requestBinding);

    void setSnmpV1Async(SnmpGetEventListener<SnmpResponse> callback,
                        Stream<SnmpSetV1RequestBinding> requestBindings);

    void setSnmpV3Async(SnmpGetEventListener<SnmpResponse> callback,
                        Stream<SnmpSetV3RequestBinding> requestBindings);
}

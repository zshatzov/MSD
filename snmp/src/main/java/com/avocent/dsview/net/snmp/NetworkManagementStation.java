package com.avocent.dsview.net.snmp;

import java.util.List;
import java.util.stream.Stream;

/**
 * <p>An interface that represents a <em>SNMP Network Management Station</em>. This interface
 * provides both synchronous and asynchronous GET operations on a SNMP agent.</p>
 *
 *
 * Created by zshatzov on 4/19/2016.
 */
public interface NetworkManagementStation {

    SnmpV1Response getSnmpV1(SnmpRequestBinding binding);

    SnmpV3Response getSnmpV3(SnmpRequestBinding binding);

    void getSnmpV1Async(SnmpGetEventListener listener, Stream<SnmpRequestBinding> bindings);

    void getSnmpV3Async(SnmpGetEventListener listener, Stream<SnmpRequestBinding> bindings);
}

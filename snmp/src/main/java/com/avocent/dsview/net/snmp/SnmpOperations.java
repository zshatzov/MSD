package com.avocent.dsview.net.snmp;

import java.util.stream.Stream;

/**
 * <p>
 * Defines a SNMP synchronous and asynchronous GET/SET operations.
 * </p>
 *
 *
 * Created by zshatzov on 4/27/2016.
 */
public interface SnmpOperations<T extends SnmpGetRequestBinding, U extends SnmpSetRequestBinding> {

        SnmpResponse get(T requestBinding);

        void getAsync(SnmpEventListener callback, Stream<T> requestBindings);

        SnmpResponse set(U requestBinding);

        void setAsync(SnmpEventListener callback, Stream<U> requestBindings);
}

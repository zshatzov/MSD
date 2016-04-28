package com.avocent.dsview.net.snmp;

import java.util.stream.Stream;

/**
 * <p>
 * A listener registered as a callback to receive an asynchronous <em>SNMP</em>
 * GET/SET responses. Since this interface is an <em>Abstract Single Method</em>,
 * the client can pass in a lambda expression which will be used as the
 * implementation of this interface.
 * </p>
 *
 *
 * Created by zshatzov on 4/19/16.
 */
@FunctionalInterface
public interface SnmpEventListener{
    /**
     * <p>
     * A callback method that passes the response(s) back to the client
     * in an asynchronous way.
     * </p>
     *
     *
     * @param results A {@link java.util.stream.Stream} of {@link SnmpResponse}
     */
    void process(Stream<SnmpResponse> results);
}

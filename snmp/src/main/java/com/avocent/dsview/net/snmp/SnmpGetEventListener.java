package com.avocent.dsview.net.snmp;

import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * A callback that passes the response(s) of an asynchronous GET/SET SNMP operation back
 * to the client. Since this interface is an <em>Abstract Single Method</em>, the client
 * can pass in a lambda expression which will be used as the implementation of this
 * interface.
 * </p>
 *
 *
 * Created by zshatzov on 4/19/16.
 */
@FunctionalInterface
public interface SnmpGetEventListener <T extends SnmpResponse> {
    /**
     * <p>A <em>Single Abstract Method Interface</em> that provides the result of a
     * <em>SNMP GET</em> request(s) to a registered client listener</p>
     *
     *
     * @param results A {@link java.util.stream.Stream} of {@link SnmpResponse}
     */
    void process(Stream<T> results);
}

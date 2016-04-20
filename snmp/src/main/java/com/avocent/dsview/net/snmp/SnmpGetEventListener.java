package com.avocent.dsview.net.snmp;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by zshatzov on 4/19/16.
 */
@FunctionalInterface
public interface SnmpGetEventListener <T extends SnmpResponse> {
    /**
     * <p>A <em>Single Abstract Method Interface</em> that provides the result of a single or multiple
     * <em>SNMP GET</em> request(s) to a registered client listener</p>
     *
     *
     * @param results A {@link java.util.stream.Stream} of {@link SnmpResponse}
     */
    void handleResult(Stream<T> results);
}

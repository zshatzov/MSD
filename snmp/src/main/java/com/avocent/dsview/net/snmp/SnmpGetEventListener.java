package com.avocent.dsview.net.snmp;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by zshatzov on 4/19/16.
 */
@FunctionalInterface
public interface SnmpGetEventListener {
    void processResults(Stream<SnmpResponse> results);
}

package com.avocent.dsview.net.snmp;

import java.util.stream.Stream;

/**
 * <p>
 * Defines synchronous and asynchronous SNMPv3 GET/SET operations.
 * </p>
 *
 * Created by zshatzov on 4/27/2016.
 */
public interface SnmpV3Operations extends  SnmpOperations<SnmpGetV3RequestBinding, SnmpSetV3RequestBinding> {
}

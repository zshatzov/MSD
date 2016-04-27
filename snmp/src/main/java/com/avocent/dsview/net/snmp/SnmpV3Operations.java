package com.avocent.dsview.net.snmp;

import java.util.stream.Stream;

/**
 * <p>
 * Defines a SNMPv3 synchronous and asynchronous GET/SET operations.
 * </p>
 *
 * Created by zshatzov on 4/27/2016.
 */
public interface SnmpV3Operations extends  SnmpOperations<SnmpGetV3RequestBinding, SnmpSetV3RequestBinding> {
}

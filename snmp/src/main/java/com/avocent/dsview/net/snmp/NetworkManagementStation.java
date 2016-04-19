package com.avocent.dsview.net.snmp;

/**
 * Created by zshatzov on 4/19/2016.
 */
public interface NetworkManagementStation {

    SnmpV1Response getSnmpV1(SnmpRequestBinding binding)
            throws  SnmpGetException;

    SnmpV3Response getSnmpV3(SnmpRequestBinding binding)
            throws  SnmpGetException;
}

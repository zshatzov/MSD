package com.avocent.dsview.net.snmp;

/**
 * Created by zshatzov on 4/19/2016.
 */
public class SnmpSetException extends RuntimeException {

    public SnmpSetException(String message){
        super(message);
    }

    public SnmpSetException(String message, Throwable cause) {
        super(message, cause);
    }
}

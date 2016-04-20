package com.avocent.dsview.net.snmp;

/**
 * Created by zshatzov on 4/19/2016.
 */
public class SnmpGetException extends RuntimeException {

    public SnmpGetException(String message){
        super(message);
    }

    public SnmpGetException(String message, Throwable cause) {
        super(message, cause);
    }
}

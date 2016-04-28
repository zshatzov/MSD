package com.avocent.dsview.net.snmp;

import java.util.stream.Stream;

/**
 * <p>
 * Defines synchronous and asynchronous SNMP GET/SET operations.
 * </p>
 *
 *
 * Created by zshatzov on 4/27/2016.
 */
public interface SnmpOperations<T extends SnmpGetRequestBinding, U extends SnmpSetRequestBinding> {

    /**
     * <p>Perform a synchronous <em>SNMP</em> GET request</p>
     *
     * @param requestBinding An object that encapsulates the parameters passed from the client
     *                       to a <em>SNMP</em> agent
     * @return An object that encapsulates the results returned from a <em>SNMP</em> agent
     *
     */
     SnmpResponse get(T requestBinding);

    /**
     * <p>Perform an asynchronous <em>SNMP</em> GET request</p>
     *
     * @param callback A callback function that will be called once the results of the asynchronous call are available
     * @param requestBindings A {@link Stream} of <em>SNMP</em> requests to process asynchronously
     *
     */
     void get(SnmpEventListener callback, Stream<T> requestBindings);

     /**
      * <p>Perform a synchronous <em>SNMP</em> SET request</p>
      *
      * @param requestBinding An object that encapsulates the parameters passed from the client
      *                       to a <em>SNMP</em> agent
      * @return An object that encapsulates the results returned from a <em>SNMP</em> agent
      *
      */
      SnmpResponse set(U requestBinding);

     /**
      * <p>Perform an asynchronous <em>SNMP</em> SET request</p>
      *
      * @param callback A callback function that will be called once the results of the asynchronous call are available
      * @param requestBindings A {@link Stream} of <em>SNMP</em> requests to process asynchronously
      *
      */
      void set(SnmpEventListener callback, Stream<U> requestBindings);
}

package com.avocent.dsview.net.snmp.impl;

import com.avocent.dsview.net.snmp.*;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * <p>
 * An implementation of {@link SnmpV3Operations} that uses the open source
 * <em>SNMP4J</em> library to implement the GET/SET operations.
 * </p>
 *
 *
 * Created by zshatzov on 4/27/2016.
 */
public class Snmp4JV3Service extends BaseSnmpService implements SnmpV3Operations {

    /**
     * <p>Perform a synchronous <em>SNMP v3</em> GET request</p>
     *
     * @param requestBinding An object that encapsulates the parameters passed from the client
     *                       to a <em>SNMP</em> agent
     * @return An object that encapsulates the results returned from a <em>SNMP</em> agent
     *
     */
    @Override
    public SnmpResponse get(SnmpGetV3RequestBinding requestBinding) {

        LOGGER.finest("Process a synchronous GET SNMPv3 request");

        if (isNull(requestBinding.getHost()) || requestBinding.getHost().isEmpty()) {
            LOGGER.severe("Host is either null or empty");
            throw new SnmpGetException("Host is missing");
        }

        final String address = String.format("udp:%s/161", requestBinding.getHost());

        Snmp snmp = null;
        try{
            final UserTarget target = createUserTarget(address, requestBinding.getUserSecurityModel());

            final ScopedPDU pdu  = new ScopedPDU();
            pdu.add(new VariableBinding(new OID(requestBinding.getOid())));

            snmp = createSnmpV3Instance(requestBinding.getEngineID());
            if(nonNull(requestBinding.getUserSecurityModel())) {
                final UsmUser usmUser = createUsmUser(requestBinding.getUserSecurityModel());
                final OctetString userName = nonNull(requestBinding.getUserSecurityModel().getUserName())?
                        new OctetString(requestBinding.getUserSecurityModel().getUserName()): null;
                snmp.getUSM().addUser(userName, usmUser);
            }

            ResponseEvent event = snmp.get(pdu, target);
            SnmpResponse response = prepareSnmpResponse(event, requestBinding);
            response.setContextEngineID(snmp.getUSM().getLocalEngineID().toString());

            return response;
        }catch(IOException e){
            LOGGER.log(Level.SEVERE, "SNMPv3 GET request failed", e);
            throw new SnmpGetException("SNMPv3 get request failed", e);
        }finally {
            if(nonNull(snmp)){
                try {
                    snmp.close();
                } catch (IOException IGNORE) {
                }
            }
        }
    }

    /**
     * <p>Perform an asynchronous <em>SNMP v3</em> GET request</p>
     *
     * @param callback A callback function that will be called once the results of the asynchronous call are available
     * @param requestBindings A {@link Stream} of <em>SNMP</em> requests to process asynchronously
     *
     */
    @Override
    public void get(SnmpEventListener callback, Stream<SnmpGetV3RequestBinding> requestBindings) {

        LOGGER.finest("Process async SNMPv3 GET requests");

        List<SnmpResponse> responses =
                requestBindings.map(binding-> prepareAsyncGetCall(binding, this::get))
                        .map(this::getAsyncResponse)
                        .collect(Collectors.toList());

        callback.process(responses.stream());
    }

    /**
     * <p>Perform a synchronous <em>SNMP v3</em> SET request</p>
     *
     * @param requestBinding An object that encapsulates the parameters passed from the client
     *                       to a <em>SNMP</em> agent
     * @return An object that encapsulates the results returned from a <em>SNMP</em> agent
     *
     */
    @Override
    public SnmpResponse set(SnmpSetV3RequestBinding requestBinding) {

        LOGGER.finest("Process SNMPv3 SET variable binding request");
        if (isNull(requestBinding.getHost()) || requestBinding.getHost().isEmpty()) {
            LOGGER.severe("Host is either null or empty");
            throw new SnmpGetException("Host is missing");
        }

        final String address = String.format("udp:%s/161", requestBinding.getHost());

        Snmp snmp = null;
        try{
            final UserTarget target = createUserTarget(address, requestBinding.getUserSecurityModel());

            final ScopedPDU pdu = new ScopedPDU();
            VariableBinding variableBinding = new VariableBinding(
                    new OID(requestBinding.getVariableBinding().getOid()));
            variableBinding.setVariable(convertVariableBinding(requestBinding.getVariableBinding()));
            pdu.add(variableBinding);

            snmp = createSnmpV3Instance(requestBinding.getEngineID());
            if(nonNull(requestBinding.getUserSecurityModel())) {
                final UsmUser usmUser = createUsmUser(requestBinding.getUserSecurityModel());
                final OctetString userName = nonNull(requestBinding.getUserSecurityModel().getUserName())?
                        new OctetString(requestBinding.getUserSecurityModel().getUserName()): null;
                snmp.getUSM().addUser(userName, usmUser);
            }

            ResponseEvent event = snmp.set(pdu, target);
            SnmpResponse response = prepareSnmpResponse(event, requestBinding);
            response.setContextEngineID(snmp.getUSM().getLocalEngineID().toString());
            return response;
        }catch(IOException e){
            LOGGER.log(Level.SEVERE, "SNMPv3 SET request failed", e);
            throw new SnmpSetException("SNMPv3 SET request failed", e);
        }finally {
            if(nonNull(snmp)){
                try {
                    snmp.close();
                } catch (IOException IGNORE) {
                }
            }
        }
    }

    /**
     * <p>Perform an asynchronous <em>SNMP v3</em> SET request</p>
     *
     * @param callback A callback function that will be called once the results of the asynchronous call are available
     * @param requestBindings A {@link Stream} of <em>SNMP</em> requests to process asynchronously
     *
     */
    @Override
    public void set(SnmpEventListener callback, Stream<SnmpSetV3RequestBinding> requestBindings) {

        LOGGER.finest("Process async SNMPv3 SET requests");

        List<SnmpResponse> responses =
                requestBindings.map(binding-> prepareAsyncSetCall(binding, this::set))
                        .map(this::getAsyncResponse)
                        .collect(Collectors.toList());

        callback.process(responses.stream());
    }
}

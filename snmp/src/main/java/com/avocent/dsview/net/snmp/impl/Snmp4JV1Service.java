package com.avocent.dsview.net.snmp.impl;

import com.avocent.dsview.net.snmp.*;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;
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
 * An implementation of {@link SnmpV1Operations} that uses the open source
 * <em>SNMP4J</em> library to implement the GET/SET operations.
 * </p>
 *
 * Created by zshatzov on 4/27/2016.
 */
public class Snmp4JV1Service extends BaseSnmpService implements SnmpV1Operations{

    /**
     * <p>Perform a synchronous <em>SNMP v1</em> GET request</p>
     *
     * @param requestBinding An object that encapsulates the parameters passed from the client
     *                       to a <em>SNMP</em> agent
     * @return An object that encapsulates the results returned from a <em>SNMP</em> agent
     *
     */
    @Override
    public SnmpResponse get(SnmpGetV1RequestBinding requestBinding) {

        LOGGER.finest("Process Get SNMPv1 request");

        if (isNull(requestBinding.getHost()) || requestBinding.getHost().isEmpty()) {
            LOGGER.severe("Host is either null or empty");
            throw new SnmpGetException("Host is missing");
        }

        final Snmp snmp;
        try {
            snmp = createSnmpV1Instance();
        }catch (IOException e){
            LOGGER.log(Level.SEVERE, "Failed to configure the transport object for SNMPv1 GET request", e);
            throw new SnmpGetException("Failed to configure UDP transport", e);
        }

        final String address = String.format("udp:%s/161", requestBinding.getHost());
        final CommunityTarget target = createCommunityTarget(
                address, requestBinding.getCommunityString());

        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(requestBinding.getOid())));

        try {

            ResponseEvent event = snmp.get(pdu, target);
            return prepareSnmpResponse(event, requestBinding);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "SNMPv1 GET request failed", e);
            throw new SnmpGetException("SNMPv1 GET request failed", e);
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
     * <p>Perform an Asynchronous <em>SNMP v1</em> GET request</p>
     *
     * @param callback A callback function that will be called once the results of the asynchronous call are available
     * @param requestBindings A {@link Stream} of <em>SNMP</em> requests to process asynchronously
     *
     */
    @Override
    public void get(SnmpEventListener callback, Stream<SnmpGetV1RequestBinding> requestBindings) {
        LOGGER.finest("Process async SNMPv1 GET requests");

        List<SnmpResponse> responses =
                requestBindings.map(binding-> prepareAsyncGetCall(binding, this::get))
                        .map(this::getAsyncResponse)
                        .collect(Collectors.toList());

        callback.process(responses.stream());
    }
    /**
     * <p>Perform a synchronous <em>SNMP v1</em> SET request</p>
     *
     * @param requestBinding An object that encapsulates the parameters passed from the client
     *                       to a <em>SNMP</em> agent
     * @return An object that encapsulates the results returned from a <em>SNMP</em> agent
     *
     */
    @Override
    public SnmpResponse set(SnmpSetV1RequestBinding requestBinding) {

        LOGGER.finest("Process SNMPv1 SET variable binding request");

        if (isNull(requestBinding.getHost()) || requestBinding.getHost().isEmpty()) {
            LOGGER.severe("Host is either null or empty");
            throw new SnmpGetException("Host is missing");
        }

        final Snmp snmp;
        try {
            snmp = createSnmpV1Instance();
        }catch (IOException e){
            LOGGER.log(Level.SEVERE, "SET SNMPv1 transport configuration failed", e);
            throw new SnmpSetException("Failed to configure UDP transport", e);
        }

        final String address = String.format("udp:%s/161", requestBinding.getHost());
        final CommunityTarget target = createCommunityTarget(
                address, requestBinding.getCommunityString());

        PDU pdu = new PDU();
        VariableBinding variableBinding = new VariableBinding(
                new OID(requestBinding.getVariableBinding().getOid()));
        variableBinding.setVariable(convertVariableBinding(requestBinding.getVariableBinding()));
        pdu.add(variableBinding);

        try {

            ResponseEvent event = snmp.set(pdu, target);
            return prepareSnmpResponse(event, requestBinding);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "SNMPv1 SET request failed", e);
            throw new SnmpGetException("SNMPv1 SET request failed", e);
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
     * <p>Perform an Asynchronous <em>SNMP v1</em> SET request</p>
     *
     * @param callback A callback function that will be called once the results of the asynchronous call are available
     * @param requestBindings A {@link Stream} of <em>SNMP</em> requests to process asynchronously
     *
     */
    @Override
    public void set(SnmpEventListener callback, Stream<SnmpSetV1RequestBinding> requestBindings) {
        LOGGER.finest("Process async SNMPv1 SET requests");

        List<SnmpResponse> responses =
                requestBindings.map(binding-> prepareAsyncSetCall(binding, this::set))
                        .map(this::getAsyncResponse)
                        .collect(Collectors.toList());

        callback.process(responses.stream());
    }
}

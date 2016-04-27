package com.avocent.dsview.net.snmp.impl;

import com.avocent.dsview.net.snmp.*;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

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
public class Snmp4JV3Operations extends BaseSnmpOperations implements SnmpV3Operations{

    @Override
    public SnmpResponse get(SnmpGetV3RequestBinding requestBinding) {

        if (isNull(requestBinding.getHost()) || requestBinding.getHost().isEmpty()) {
            LOGGER.severe("Host is either null or empty");
            throw new SnmpGetException("Host is missing");
        }

        final TransportMapping<UdpAddress> transport;
        final Snmp snmp;
        final USM usm;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            if(nonNull(requestBinding.getEngineID())){
                usm = new USM(SecurityProtocols.getInstance(),
                        new OctetString(requestBinding.getEngineID()), 0);
            }else {
                LOGGER.finest("Create USM with default local engine ID");
                usm = new USM(SecurityProtocols.getInstance(),
                        new OctetString(MPv3.createLocalEngineID()), 0);
            }
            SecurityModels.getInstance().addSecurityModel(usm);
            transport.listen();
        }catch (IOException e){
            LOGGER.log(Level.SEVERE, "Failed to configure the transport object for SNMPv3 GET request", e);
            throw new SnmpGetException("Failed to configure UDP transport", e);
        }

        final String address = String.format("udp:%s/161", requestBinding.getHost());

        final UserTarget target;
        final ScopedPDU pdu;
        try{
            target = createUserTarget(address, requestBinding.getUserSecurityModel());

            pdu = new ScopedPDU();
            pdu.add(new VariableBinding(new OID(requestBinding.getOid())));

            if(nonNull(requestBinding.getUserSecurityModel())) {
                final UsmUser usmUser = createUsmUser(requestBinding.getUserSecurityModel());
                final OctetString userName = nonNull(requestBinding.getUserSecurityModel().getUserName())?
                        new OctetString(requestBinding.getUserSecurityModel().getUserName()): null;
                snmp.getUSM().addUser(userName, usmUser);
            }

            ResponseEvent event = snmp.get(pdu, target);
            SnmpResponse response = prepareSnmpResponse(event, requestBinding);
            response.setContextEngineID(usm.getLocalEngineID().toString());

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

    @Override
    public void getAsync(SnmpEventListener callback, Stream<SnmpGetV3RequestBinding> requestBindings) {

        LOGGER.finest("Process async SNMPv3 GET requests");

        List<SnmpResponse> responses =
                requestBindings.map(binding-> prepareAsyncGetCall(binding, this::get))
                        .map(this::getAsyncResponse)
                        .collect(Collectors.toList());

        callback.process(responses.stream());
    }

    @Override
    public SnmpResponse set(SnmpSetV3RequestBinding requestBinding) {

        LOGGER.finest("Process SNMPv3 SET variable binding request");
        if (isNull(requestBinding.getHost()) || requestBinding.getHost().isEmpty()) {
            LOGGER.severe("Host is either null or empty");
            throw new SnmpGetException("Host is missing");
        }

        final TransportMapping<UdpAddress> transport;
        final Snmp snmp;
        final USM usm;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            if(nonNull(requestBinding.getEngineID())){
                usm = new USM(SecurityProtocols.getInstance(),
                        new OctetString(requestBinding.getEngineID()), 0);
            }else {
                LOGGER.finest("Create USM with default local engine ID");
                usm = new USM(SecurityProtocols.getInstance(),
                        new OctetString(MPv3.createLocalEngineID()), 0);
            }
            SecurityModels.getInstance().addSecurityModel(usm);
            transport.listen();
        }catch (IOException e){
            LOGGER.log(Level.SEVERE, "SET SNMPv3 transport configuration failed", e);
            throw new SnmpSetException("Failed to configure UDP transport", e);
        }

        final String address = String.format("udp:%s/161", requestBinding.getHost());

        final UserTarget target;
        final ScopedPDU pdu;
        try{
            target = createUserTarget(address, requestBinding.getUserSecurityModel());

            pdu = new ScopedPDU();
            VariableBinding variableBinding = new VariableBinding(
                    new OID(requestBinding.getVariableBinding().getOid()));
            variableBinding.setVariable(convertVariableBinding(requestBinding.getVariableBinding()));
            pdu.add(variableBinding);

            if(nonNull(requestBinding.getUserSecurityModel())) {
                final UsmUser usmUser = createUsmUser(requestBinding.getUserSecurityModel());
                final OctetString userName = nonNull(requestBinding.getUserSecurityModel().getUserName())?
                        new OctetString(requestBinding.getUserSecurityModel().getUserName()): null;
                snmp.getUSM().addUser(userName, usmUser);
            }

            ResponseEvent event = snmp.set(pdu, target);
            SnmpResponse response = prepareSnmpResponse(event, requestBinding);
            response.setContextEngineID(usm.getLocalEngineID().toString());
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

    @Override
    public void setAsync(SnmpEventListener callback, Stream<SnmpSetV3RequestBinding> requestBindings) {

        LOGGER.finest("Process async SNMPv3 SET requests");

        List<SnmpResponse> responses =
                requestBindings.map(binding-> prepareAsyncSetCall(binding, this::set))
                        .map(this::getAsyncResponse)
                        .collect(Collectors.toList());

        callback.process(responses.stream());
    }
}

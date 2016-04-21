package com.avocent.dsview.net.snmp.impl;

import static org.snmp4j.mp.SnmpConstants.*;
import static java.util.Objects.*;

import com.avocent.dsview.net.snmp.*;
import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.MessageProcessingModel;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Created by zshatzov on 4/19/2016.
 *
 *
 * <p>
 *     An implementation of a <code>SNMP Network Management Station</code>.
 *     This implementation uses an open source library <em>SNMP4J</em> to perform
 *     GET/SET request on a SNMP agent.
 * </p>
 */
public class NetworkManagementStationImpl implements NetworkManagementStation{

    private static final Logger LOGGER = Logger.getLogger(NetworkManagementStationImpl.class.getName());

    /**
     * <p>A synchronous SNMPv1 get request</p>
     *
     * @param binding An object that encapsulates the variable binding and agent info
     * @return SnmpV1Response
     */
    @Override
    public SnmpV1Response getSnmpV1(final SnmpRequestBinding binding){

        LOGGER.finest("Process Get SNMPv1 request");

        if (null == binding.getHost() || binding.getHost().isEmpty()) {
            LOGGER.severe("Host is null or empty");
            throw new SnmpGetException("Host is missing");
        }

        final TransportMapping<UdpAddress> transport;
        final Snmp snmp;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();
        }catch (IOException e){
            LOGGER.log(Level.SEVERE, "GET SNMPv1 transport configuration failed", e);
            throw new SnmpGetException("Failed to configure UDP transport", e);
        }

        final String address = String.format("udp:%s/161", binding.getHost());
        CommunityTarget target = new CommunityTarget(GenericAddress.parse(address),
                new OctetString(binding.getCommunityString()));
        target.setRetries(2);
        target.setTimeout(1000);
        target.setVersion(SnmpConstants.version1);

        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(binding.getOid())));
        pdu.setType(PDU.GET);

        try {
            ResponseEvent event = snmp.send(pdu, target);
            String requestId = event.getResponse().getRequestID().toString();
            int errorStatusCode = event.getResponse().getErrorStatus();
            String errorStatusMessage = event.getResponse().getErrorStatusText();
            SnmpV1Response response = new SnmpV1Response(binding.getClientId(),
                    requestId, errorStatusMessage, errorStatusCode);
            for(VariableBinding vb: event.getResponse().getVariableBindings()){
                String oid  = vb.getOid().toString();
                String value = vb.toValueString();
                String type = vb.getVariable().getSyntaxString();
                response.addVariableBinding(oid, value, type);
            }

            LOGGER.finest("Successfully processed SNMPv1 get request");

            return response;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "SNMPv1 get request failed", e);
            throw new SnmpGetException("SNMPv1 get request failed", e);
        }finally {
            if(null != snmp){
                try {
                    snmp.close();
                } catch (IOException IGNORE) {
                }
            }
        }
    }

    /**
     * <p>A synchronous SNMPv3 get request</p>
     *
     * @param binding An object that encapsulates the variable binding and agent info
     * @return SnmpV3Response
     */
    @Override
    public SnmpV3Response getSnmpV3(final SnmpRequestBinding binding){

        if (null == binding.getHost() || binding.getHost().isEmpty()) {
            throw new SnmpGetException("Host is missing");
        }

        final TransportMapping<UdpAddress> transport;
        final Snmp snmp;
        final USM usm;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            usm = new USM(SecurityProtocols.getInstance(),
                    new OctetString(MPv3.createLocalEngineID()), 0);
            SecurityModels.getInstance().addSecurityModel(usm);
            transport.listen();
        }catch (IOException e){
            throw new SnmpGetException("Failed to configure UDP transport", e);
        }

        final String address = String.format("udp:%s/161", binding.getHost());

        final UserTarget target;
        final ScopedPDU pdu;
        try{
            target = new UserTarget();
            target.setAddress(GenericAddress.parse(address));
            target.setRetries(2);
            target.setTimeout(1000);
            target.setVersion(SnmpConstants.version3);
            target.setSecurityModel(MessageProcessingModel.MPv3);
            target.setSecurityLevel(binding.getUserSecurityModel().getSecurityLevel().ordinal());

            pdu = new ScopedPDU();
            pdu.setType(PDU.GET);
            pdu.add(new VariableBinding(new OID(binding.getOid())));

            if(nonNull(binding.getUserSecurityModel())) {
                LOGGER.finest("Setup USM user credetinals to establish secure communications");
                final OctetString userName = nonNull(binding.getUserSecurityModel().getUserName())?
                        new OctetString(binding.getUserSecurityModel().getUserName()): null;
                final OctetString securityName = nonNull(binding.getUserSecurityModel().getSecurityName())?
                        new OctetString(binding.getUserSecurityModel().getSecurityName()): null;
                final OID authProtocol = nonNull(binding.getUserSecurityModel().getAuthenticationProtocol())?
                        new OID(binding.getUserSecurityModel().getAuthenticationProtocol().getID()): null;
                final OctetString authPassphrase = nonNull(binding.getUserSecurityModel().getAuthenticationPassphrase())?
                        new OctetString(binding.getUserSecurityModel().getAuthenticationPassphrase()): null;
                final OID privProtocol = nonNull(binding.getUserSecurityModel().getPrivacyProtocol())?
                        new OID(binding.getUserSecurityModel().getPrivacyProtocol().getID()): null;
                final OctetString privPassphrase = nonNull(binding.getUserSecurityModel().getPrivacyPassphrase())?
                        new OctetString(binding.getUserSecurityModel().getPrivacyPassphrase()): null;

                snmp.getUSM().addUser(userName,
                        new UsmUser(securityName, authProtocol, authPassphrase, privProtocol,privPassphrase)
                );
            }

            ResponseEvent event = snmp.send(pdu, target);
            final String requestId;
            final int errorStatusCode;
            final String errorStatusMessage;
            if(nonNull(event) && nonNull(event.getResponse())) {
                requestId = nonNull(event.getResponse().getRequestID()) ?
                        event.getResponse().getRequestID().toString() : null;
                errorStatusCode = event.getResponse().getErrorStatus();
                errorStatusMessage = event.getResponse().getErrorStatusText();
            }else{
                requestId = null;
                errorStatusCode = SNMP_ERROR_GENERAL_ERROR;
                errorStatusMessage = String.format(
                     "Failed to retrieve variable binding for host (%s) and OID (%s)",
                        binding.getHost(), binding.getOid());
            }

            SnmpV3Response response = new SnmpV3Response(usm.getLocalEngineID().toString(),
                  binding.getClientId(), requestId, errorStatusMessage, errorStatusCode);

            if(nonNull(event) && nonNull(event.getResponse())) {
                for (VariableBinding vb : event.getResponse().getVariableBindings()) {
                    String oid = vb.getOid().toString();
                    String value = vb.toValueString();
                    String type = vb.getVariable().getSyntaxString();
                    response.addVariableBinding(oid, value, type);
                }
            }
            return response;
        }catch(IOException e){
            throw new SnmpGetException("SnmpV3 get request failed", e);
        }finally {
            if(null != snmp){
                try {
                    snmp.close();
                } catch (IOException IGNORE) {
                }
            }
        }
    }

    /**
     * <p>A method that processes multiple asynchronous SNMPv1 GET requests</p>
     *
     * @param listener A callback component that will be invoked once the asynchronous SNMPv1 GET request are processed
     * @param bindings One or more SNMPv1 request to be processed
     */
    @Override
    public void getSnmpV1Async(final SnmpGetEventListener<SnmpV1Response> listener, final Stream<SnmpRequestBinding> bindings) {

        LOGGER.finest("Process async SNMPv1 requests");

        final List<SnmpV1Response> responses = new ArrayList<>();
        bindings.forEach(binding -> {
                CompletableFuture<SnmpV1Response> completableFuture =
                     CompletableFuture.supplyAsync(() -> {return getSnmpV1(binding);});

                try {
                     responses.add(completableFuture.get());
                }catch (InterruptedException | ExecutionException e){
                    LOGGER.log(Level.SEVERE,
                            "Failed to retrieve async result for SNMPv1 GET request", e);
                }
        });

        listener.process(responses.stream());
    }

    /**
     * <p>A method that processes multiple asynchronous SNMPv3 GET requests</p>
     *
     * @param listener A callback component that will be invoked once the asynchronous SNMPv1 GET request are processed
     * @param bindings One or more SNMPv1 request to be processed
     */
    @Override
    public void getSnmpV3Async(final SnmpGetEventListener<SnmpV3Response> listener, final Stream<SnmpRequestBinding> bindings) {
        LOGGER.finest("Process async SNMPv3 requests");

        final List<SnmpV3Response> responses = new ArrayList<>();
        bindings.forEach(binding -> {
            CompletableFuture<SnmpV3Response> completableFuture =
                    CompletableFuture.supplyAsync(() -> {return getSnmpV3(binding);});

            try {
                responses.add(completableFuture.get());
            }catch (InterruptedException | ExecutionException e){
                LOGGER.log(Level.SEVERE,
                        "Failed to retrieve async result for SNMPv3 GET request", e);
            }
        });

        listener.process(responses.stream());
    }
}

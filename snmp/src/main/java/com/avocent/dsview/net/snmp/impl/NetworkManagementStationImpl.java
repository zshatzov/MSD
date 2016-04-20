package com.avocent.dsview.net.snmp.impl;

import com.avocent.dsview.net.snmp.*;
import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.MessageProcessingModel;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
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
 *     An implementation of a <code>SNMP Network Management Station component</code>.
 *     This implementation uses an open source library <em>SNMP4J</em> to perform
 *     GET/SET request on SNMP agents.
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

            pdu = new ScopedPDU();
            pdu.setType(PDU.GET);
            pdu.add(new VariableBinding(new OID(binding   /**
     * <p>A synchronous SNMPv1 get request</p>
     *
     * @param binding An object that encapsulates the variable binding and agent info
     * @return SnmpV1Response
     */.getOid())));

            ResponseEvent event = snmp.send(pdu, target);
            String requestId = event.getResponse().getRequestID().toString();
            int errorStatusCode = event.getResponse().getErrorStatus();
            String errorStatusMessage = event.getResponse().getErrorStatusText();
            SnmpV3Response response = new SnmpV3Response(usm.getLocalEngineID().toString(),
                  binding.getClientId(), requestId, errorStatusMessage, errorStatusCode);
            for(VariableBinding vb: event.getResponse().getVariableBindings()){
                String oid  = vb.getOid().toString();
                String value = vb.toValueString();
                String type = vb.getVariable().getSyntaxString();
                response.addVariableBinding(oid, value, type);
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
    public void getSnmpV1Async(SnmpGetEventListener listener, Stream<SnmpRequestBinding> bindings) {
        final List<SnmpResponse> responses = new ArrayList<>();
        bindings.forEach(binding -> {
                CompletableFuture<SnmpV1Response> completableFuture =
                     CompletableFuture.supplyAsync(() -> {return getSnmpV1(binding);});

                try {
                     responses.add(completableFuture.get());
                }catch (InterruptedException | ExecutionException IGNORE){
                }
        });

        listener.processResults(responses.stream());
    }
}
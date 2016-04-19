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

/**
 * Created by zshatzov on 4/19/2016.
 */
public class NetworkManagementStationImpl implements NetworkManagementStation{
    @Override
    public SnmpV1Response getSnmpV1(final SnmpRequestBinding binding)
            throws SnmpGetException {

        if (null == binding.getHost() || binding.getHost().isEmpty()) {
            throw new SnmpGetException("Host is missing");
        }

        final TransportMapping<UdpAddress> transport;
        final Snmp snmp;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();
        }catch (IOException e){
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
            return response;
        } catch (IOException e) {
            throw new SnmpGetException("SnmpV1 get request failed", e);
        }finally {
            if(null != snmp){
                try {
                    snmp.close();
                } catch (IOException IGNORE) {
                }
            }
        }
    }

    @Override
    public SnmpV3Response getSnmpV3(final SnmpRequestBinding binding)
            throws SnmpGetException {

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
            pdu.add(new VariableBinding(new OID(binding.getOid())));

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
}

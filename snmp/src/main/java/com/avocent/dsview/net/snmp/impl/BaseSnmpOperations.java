package com.avocent.dsview.net.snmp.impl;

import com.avocent.dsview.net.snmp.*;
import org.snmp4j.CommunityTarget;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MessageProcessingModel;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.*;
import org.snmp4j.smi.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.logging.Logger;

import static java.util.Objects.nonNull;
import static org.snmp4j.mp.SnmpConstants.SNMP_ERROR_GENERAL_ERROR;

/**
 * <p>
 * An abstract class that encapsulates common operations.
 * </p>
 *
 * Created by zshatzov on 4/27/2016.
 */
public abstract class BaseSnmpOperations {

    protected final Logger LOGGER = Logger.getLogger(getClass().getName());

    protected SnmpResponse getAsyncResponse(CompletableFuture<SnmpResponse> future){
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new SnmpGetException("Failed to retrieve async GET response", e);
        }
    }

    protected <T extends SnmpGetRequestBinding> CompletableFuture<SnmpResponse> prepareAsyncGetCall(
            T binding, Function<T, SnmpResponse> handler){
        CompletableFuture<SnmpResponse> completableFuture =
                CompletableFuture.supplyAsync(() -> {return handler.apply(binding);});

        return completableFuture;
    }

    protected <T extends SnmpSetRequestBinding> CompletableFuture<SnmpResponse> prepareAsyncSetCall(
            T binding, Function<T, SnmpResponse> handler){
        CompletableFuture<SnmpResponse> completableFuture =
                CompletableFuture.supplyAsync(() -> {return handler.apply(binding);});

        return completableFuture;
    }

    protected CommunityTarget createCommunityTarget(String address, String communityString){
        CommunityTarget target = new CommunityTarget(GenericAddress.parse(address),
                new OctetString(communityString));
        target.setRetries(2);
        target.setTimeout(1000);
        target.setVersion(SnmpConstants.version1);

        return target;
    }

    protected UserTarget createUserTarget(String address, UserSecurityModel usm){

        final UserTarget target = new UserTarget();
        target.setAddress(GenericAddress.parse(address));
        target.setRetries(3);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version3);
        target.setSecurityModel(MessageProcessingModel.MPv3);

        if(nonNull(usm) && nonNull(usm.getSecurityLevel())) {
            target.setSecurityLevel(usm.getSecurityLevel().ordinal());
        }

        if(nonNull(usm) && nonNull(usm.getSecurityName())){
            target.setSecurityName(
                    new OctetString(usm.getSecurityName()));
        }

        return target;
    }

    protected UsmUser createUsmUser(final UserSecurityModel usm){
        LOGGER.finest("Setup USM user credetinals to establish secure communications");
        final OctetString securityName = nonNull(usm.getSecurityName())?
                new OctetString(usm.getSecurityName()): null;

        final OID authProtocol;
        switch(usm.getAuthenticationProtocol()){
            case MD5: authProtocol = AuthMD5.ID; break;
            case SHA: authProtocol = AuthSHA.ID; break;
            default:authProtocol = null;
        }
        final OctetString authPassphrase = nonNull(usm.getAuthenticationPassphrase())?
                new OctetString(usm.getAuthenticationPassphrase()): null;

        final OID privProtocol;
        switch (usm.getPrivacyProtocol()){
            case AES128: privProtocol = PrivAES128.ID; break;
            case AES192: privProtocol = PrivAES192.ID; break;
            case AES256: privProtocol = PrivAES256.ID; break;
            case DES: privProtocol = PrivDES.ID; break;
            default: privProtocol = null;
        }
        final OctetString privPassphrase = nonNull(usm.getPrivacyPassphrase())?
                new OctetString(usm.getPrivacyPassphrase()): null;

        return new UsmUser(securityName, authProtocol, authPassphrase,
                privProtocol,privPassphrase);
    }

    protected Variable convertVariableBinding(SnmpInputVariableBinding binding){
        switch (binding.getVariableType()){
            case Counter32: return new Counter32(Integer.valueOf(binding.getValue()));
            case Counter64: return new Counter64(Long.valueOf(binding.getValue()));
            case Gauge32: return new Gauge32(Integer.valueOf(binding.getValue()));
            case Integer32: return new Integer32(Integer.valueOf(binding.getValue()));
            case IpAddress: return new IpAddress(binding.getValue());
            case OctetString: return new OctetString(binding.getValue());
            case OID: return new OID(binding.getValue());
            case Opaque: return new Opaque(binding.getValue().getBytes());
            case SshAddress: return new SshAddress(binding.getValue());
            case TcpAddress: return new TcpAddress(binding.getValue());
            case TimeTicks: return new TimeTicks(Long.valueOf(binding.getValue()));
            case TlsAddress: return new TlsAddress(binding.getValue());
            case UdpAddress: return new UdpAddress(binding.getValue());
            case UnsignedInteger32: return new UnsignedInteger32(Math.abs(Integer.valueOf(binding.getValue())));
            default: throw new SnmpSetException("Invalid variable type for SET request");
        }
    }

    protected <T extends SnmpRequestBinding> SnmpResponse prepareSnmpResponse(
            ResponseEvent event, T request){
        final int errorStatusCode;
        final String errorStatusMessage;
        if(nonNull(event) && nonNull(event.getResponse())) {
            errorStatusCode = event.getResponse().getErrorStatus();
            errorStatusMessage = event.getResponse().getErrorStatusText();
        }else{
            errorStatusCode = SNMP_ERROR_GENERAL_ERROR;
            errorStatusMessage = String.format(
                    "Failed to retrieve variable binding from host (%s)", request.getHost());
        }

        SnmpResponse response = new SnmpResponse(request.getClientID(),
                errorStatusMessage, errorStatusCode);

        if(nonNull(event) && nonNull(event.getResponse())){
            for(VariableBinding vb: event.getResponse().getVariableBindings()){
                String oid  = vb.getOid().toString();
                String value = vb.toValueString();
                String type = vb.getVariable().getSyntaxString();
                SnmpOutputVariableBinding getVariableBinding =
                        new SnmpOutputVariableBinding(oid, value, type);
                response.addVariableBinding(getVariableBinding);
            }
        }

        return response;
    }
}


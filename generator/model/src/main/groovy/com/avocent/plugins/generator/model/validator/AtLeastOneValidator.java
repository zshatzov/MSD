package com.avocent.plugins.generator.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avocent.plugins.generator.model.common.SNMPConfiguration;

public class AtLeastOneValidator implements ConstraintValidator<AtLestOne, SNMPConfiguration> {
	
	private static final Logger LOG = LoggerFactory.getLogger(AtLeastOneValidator.class); 

	@Override
	public void initialize(AtLestOne constraintAnnotation) {
	}

	@Override
	public boolean isValid(SNMPConfiguration snmpConfiguration, ConstraintValidatorContext context) {
		
		LOG.debug("Validate that either snmp1 or snmp3 check box are selected");
		
		if(snmpConfiguration.isIsSNMPv1Supported() || snmpConfiguration.isIsSNMPv3Supported()){
			return true;
		}		

		return false;
	}	
}

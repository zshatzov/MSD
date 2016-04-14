package com.avocent.plugins.generator.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avocent.plugins.generator.model.view.ElementViewComponent;
import com.avocent.plugins.generator.model.view.MetadataViewComponent;

public class ViewComponentValidator implements ConstraintValidator<ViewComponentRequired, ElementViewComponent> {
	
	private static final Logger LOG = LoggerFactory.getLogger(ViewComponentValidator.class); 
	
	@Override
	public boolean isValid(ElementViewComponent viewElement, ConstraintValidatorContext context) {
		LOG.debug("Validate element view component against a required constraint");
		
		if(null == viewElement.getMetadata()){
			return true;
		}
		
		context.disableDefaultConstraintViolation();
		
		MetadataViewComponent metaData = viewElement.getMetadata();
		if(metaData.isRequired()){		
			if(null == viewElement.getValue() || viewElement.getValue().isEmpty()){				
				context.buildConstraintViolationWithTemplate(
						viewElement.getLabel() +  " must not be blank").addConstraintViolation();
				return false;
			}else{
				return true;
			}
			
		}else{
			return true;
		}
	}



	@Override
	public void initialize(ViewComponentRequired constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}
}

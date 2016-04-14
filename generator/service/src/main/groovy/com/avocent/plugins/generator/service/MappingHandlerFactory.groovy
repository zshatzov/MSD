package com.avocent.plugins.generator.service

import groovy.util.logging.Slf4j

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.avocent.plugins.generator.model.ProjectType
import com.avocent.plugins.generator.model.pdu.MIBMappingInformation as PDUMappings
import com.avocent.plugins.generator.model.ups.MIBMappingInformation as UPSMappings
import com.avocent.plugins.generator.model.view.ContainerViewComponent
import com.avocent.plugins.generator.model.view.ElementViewComponent
import com.avocent.plugins.generator.model.view.GroupViewComponent

/**
 * <p>
 * An <code>Enum</code> that provides different adapter strategies for converting
 * a list of {@link ContainerViewComponent} instances to a specific <code>JAXB</code>
 * object that represents a particular type of device. 
 * </p>
 * 
 * @author zshatzov
 *
 */
@Slf4j('LOG')
enum MappingHandlerFactory {	
		
	PDUMappingHandler{

		@Override
		Object mapObject(List<ContainerViewComponent> mapping){		
			LOG.debug("Processing mapping for a PDU device..")	
			PDUMappings mibMapping = new PDUMappings(deviceType: ProjectType.PDU.toString())
			def packageName = mibMapping.getClass().package.name
			mapping.each {ContainerViewComponent cvc -> 
				def container = Class.forName("${packageName}.${cvc.name}").newInstance()
				if(cvc.name.startsWith('PDU')){
					mibMapping["${cvc.name[0..2].toLowerCase()}${cvc.name[3..-1]}"] = container	
				}else{
					mibMapping["${cvc.name[0].toLowerCase()}${cvc.name[1..-1]}"] = container
				}
				cvc.groups.each{GroupViewComponent gvc->
					def group = Class.forName("${packageName}.${gvc.group}").newInstance()
					if(gvc.group.startsWith('PDU')){
						container["${gvc.group[0..2].toLowerCase()}${gvc.group[3..-1]}"] = group
					}else{
						container["${gvc.group[0].toLowerCase()}${gvc.group[1..-1]}"] = group
					}
					gvc.elements.each{ElementViewComponent evc->
						if(evc.value){						
							group[evc.name] = evc.value
						}
					}
				}
			}
			
			return mibMapping		
		}
	},
	UPSMappingHandler{

		@Override
		Object mapObject(List<ContainerViewComponent> mapping){
				LOG.debug("Processing mapping for a UPS device..")	
			UPSMappings mibMapping = new UPSMappings(deviceType: ProjectType.UPS.toString())
			def packageName = mibMapping.getClass().package.name
			mapping.each {ContainerViewComponent cvc -> 
				def container = Class.forName("${packageName}.${cvc.name}").newInstance()
				if(cvc.name.startsWith('UPS') || cvc.name.startsWith('RFC')){
					mibMapping["${cvc.name[0..2].toLowerCase()}${cvc.name[3..-1]}"] = container	
				}else{
					mibMapping["${cvc.name[0].toLowerCase()}${cvc.name[1..-1]}"] = container
				}
				cvc.groups.each{GroupViewComponent gvc->
					def group = Class.forName("${packageName}.${gvc.group}").newInstance()
					if(gvc.group.startsWith('UPS') || gvc.group.startsWith('RFC')){
						container["${gvc.group[0..2].toLowerCase()}${gvc.group[3..-1]}"] = group
					}else{
						container["${gvc.group[0].toLowerCase()}${gvc.group[1..-1]}"] = group
					}
					gvc.elements.each{ElementViewComponent evc->
						if(evc.value){						
							group[evc.name] = evc.value
						}
					}
				}
			}
			
			return mibMapping				
		}
	};	
	
	public static MappingHandlerFactory getHanlder(ProjectType projectType){		
		switch(projectType){
			case ProjectType.PDU:
				return PDUMappingHandler
			case ProjectType.UPS:
				return UPSMappingHandler
			default:
				throw new UnsupportedOperationException(
					String.format("(%s) is not supported", projectType))
		}	 
	}
	
	abstract Object mapObject(List<ContainerViewComponent> mapping);
}

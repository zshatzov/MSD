package com.avocent.plugins.generator.service

import groovy.util.logging.Slf4j

import javax.xml.bind.JAXBElement
import javax.xml.transform.stream.StreamSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.oxm.jaxb.Jaxb2Marshaller
import org.springframework.stereotype.Service

import com.avocent.plugins.generator.model.Project
import com.avocent.plugins.generator.model.ProjectType
import com.avocent.plugins.generator.model.pdu.MIBMappingInformation as PDUMapping
import com.avocent.plugins.generator.model.ups.MIBMappingInformation as UPSMapping
import com.avocent.plugins.generator.model.view.ContainerViewComponent

/**
 * <p>
 * A service that populates a newly created {@link Project} with datapoint mappings
 * loaded from an external XML file.
 * </p>
 * 
 * @author zshatzov
 *
 */
@Service	
@Slf4j('LOG')	
class ProjectDatapointPopulater {
	
	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller
	
	@Autowired
	private DeviceViewService deviceViewService

	ProjectType retrieveProjectType(final File mappingFile){
		
		LOG.debug("Processing mapping file (${mappingFile.name})")
		
		def root = new XmlSlurper().parse(mappingFile)
		
		def projectType = root[0]?.children()?.first()?.text() as ProjectType
		
		if(!projectType){
			throw new IOException('File doesn\'t specify a DeviceType element')
		}
		
		if(!(projectType.toString() in deviceViewService.supportedProjectTypes())){
			throw new IOException('File contains unsupported DeviceType element value')
		}
		
		return projectType
	}
			
	void populateWithMappings(final Project project, final File mappingFile){
		
		LOG.debug("Populate Project with datapoint mappings from file...")
			 
		def obj
		if(project.type == ProjectType.PDU){
			JAXBElement<PDUMapping> element = jaxb2Marshaller.unmarshal(
						new StreamSource(mappingFile))
			obj = element.value
			populatePDUMappings( obj, project.dataPointMapping)
		}else if(project.type == ProjectType.UPS){
			JAXBElement<UPSMapping> element = jaxb2Marshaller.unmarshal(
					new StreamSource(mappingFile))
			obj = element.value
			populateUPSMappings(obj, project.dataPointMapping)
		}
		
		project.nmmMapping = obj.nmmxmlInformation
		project.snmpMapping = obj.snmpConfiguration
		project.obwiMapping = obj.obwiConfiguration
		project.trapsMapping = obj.trapsInformation 
	}
	
	private def populatePDUMappings(final PDUMapping mapping, 
				final List<ContainerViewComponent> datapoints){

		LOG.debug('Populate Discvoery Feature Information for PDU')
		ContainerViewComponent discovery =
			 datapoints.find{ it.name == mapping.discoveryFeatureInformation.getClass().simpleName}			
		
		mapping.discoveryFeatureInformation.with{
			discovery.groups.find{
				it.group == appliancePropertiesInformation.getClass().simpleName
			}?.elements.each{it.value = appliancePropertiesInformation[it.name]?:''}	  	 			  
				
			discovery.groups.find{
				it.group == pduInformation.getClass().simpleName
			}?.elements.each{it.value = pduInformation[it.name]?:''}
			
			discovery.groups.find{
				it.group == outletInformation.getClass().simpleName
			}?.elements.each{it.value = outletInformation[it.name]?:''}
		}
		
		LOG.debug('Populate Power Opreations Information for PDU')
		ContainerViewComponent powerOperations =
			 datapoints.find{ it.name == mapping.powerOperationsInformation.getClass().simpleName}
			
		mapping.powerOperationsInformation.with{
			powerOperations.groups.find{
				it.group ==	pduCapabilitiesInformation.getClass().simpleName
			}?.elements.each{it.value =  pduCapabilitiesInformation[it.name]?:''}			 
		 
			powerOperations.groups.find{
				it.group == pduOutletsCapabilitiesInformation.getClass().simpleName
			}?.elements.each{it.value = pduOutletsCapabilitiesInformation[it.name]?:''}
			
			powerOperations.groups.find{
				it.group == pduOperationsInformation.getClass().simpleName
			}?.elements.each{it.value = pduOperationsInformation[it.name]?:''}
			
			powerOperations.groups.find{
				it.group == pduOutletsOperationsInformation.getClass().simpleName
			}?.elements.each{it.value = pduOutletsOperationsInformation[it.name]?:''}
		}
		
		LOG.debug('Populate Power Collection Information for PDU')		
		ContainerViewComponent powerCollection = datapoints.find{				 
			it.name == mapping.powerCollectionInformation.getClass().simpleName
		}
			 
		mapping.powerCollectionInformation.with{
			powerCollection.groups.find{
				it.group == propertyInformation.getClass().simpleName
			}?.elements.each{it.value = propertyInformation[it.name]?:''}
			
			powerCollection.groups.find{
				it.group == powerOverallInformation.getClass().simpleName
			}?.elements.each{it.value = powerOverallInformation[it.name]?:''}
			
			powerCollection.groups.find{
				it.group == powerPerCircuitInformation.getClass().simpleName
			}?.elements.each{it.value = powerPerCircuitInformation[it.name]?:''}
			
			powerCollection.groups.find{
				it.group == powerPerPhaseInformation.getClass().simpleName
			}?.elements.each{it.value = powerPerPhaseInformation[it.name]?:''}
			
			powerCollection.groups.find{
				it.group == powerPerOutletInformation.getClass().simpleName
			}?.elements.each{it.value = powerPerOutletInformation[it.name]?:''}
			
			powerCollection.groups.find{
				it.group == environmentInformation.getClass().simpleName 
			}?.elements.each{it.value = environmentInformation[it.name]?:''}	
		}
	}
	
	private def populateUPSMappings(final UPSMapping mapping, 
				final List<ContainerViewComponent> datapoints){
				
		LOG.debug('Populate Discvoery Feature Information for UPS')
		ContainerViewComponent discovery =
			datapoints.find{it.name == mapping.discoveryFeatureInformation.getClass().simpleName}
			
		mapping.discoveryFeatureInformation.with{		
			discovery.groups.find{
				it.group == appliancePropertiesInformation.getClass().simpleName
			}?.elements.each{it.value = appliancePropertiesInformation[it.name]?:''}
			
			discovery.groups.find{
				it.group == upsInformation.getClass().simpleName
			}?.elements.each{it.value = upsInformation[it.name]?:''}
		}
		
		LOG.debug('Populate Power Collection Information for UPS')
		ContainerViewComponent powerCollection =
			datapoints.find{it.name ==  mapping.powerCollectionInformation.getClass().simpleName}
		
		mapping.powerCollectionInformation.with{			
			powerCollection.groups.find{
				it.group == propertyInformation.getClass().simpleName
			}?.elements.each{it.value = propertyInformation[it.name]?:''}	
			
			powerCollection.groups.find{
				it.group == environmentInformation.getClass().simpleName
			}?.elements.each{it.value = environmentInformation[it.name]?:''}
		}
		
		LOG.debug('Populate RFC1628 Information for UPS')
		ContainerViewComponent rfc1628 =
			datapoints.find{it.name == mapping.rfc1628Information.getClass().simpleName}
		
		mapping.rfc1628Information.with{				
			rfc1628.groups.find{it.group == upsIdent.getClass().simpleName				
				}?.elements.each{it.value = upsIdent[it.name]?:''}
			
			rfc1628.groups.find{
				it.group == upsBatteryInformation.getClass().simpleName
			}?.elements.each{it.value = upsBatteryInformation[it.name]?:''}
			
			rfc1628.groups.find{
				it.group == upsInputInformation.getClass().simpleName
			}?.elements.each{it.value = upsInputInformation[it.name]?:''}
			
			rfc1628.groups.find{
				it.group == upsOutputInformation.getClass().simpleName
			}?.elements.each{it.value = upsOutputInformation[it.name]?:''}
			
			rfc1628.groups.find{
				it.group == upsBypassInformation.getClass().simpleName
			}?.elements.each{it.value = upsBypassInformation[it.name]?:''}		
			
			rfc1628.groups.find{
				it.group == upsControlInformation.getClass().simpleName
			}?.elements.each{it.value = upsControlInformation[it.name]?:''}
		}
	}
}

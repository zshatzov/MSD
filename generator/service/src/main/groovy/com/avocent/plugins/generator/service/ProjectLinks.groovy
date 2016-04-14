package com.avocent.plugins.generator.service

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode

import org.springframework.stereotype.Service

@Service
class ProjectLinks {
	
	enum LinkKey{
		bundlesExist, customCodeMappings, datapointsMappings, deleteCustomCode, deleteMibFile,
		deleteProject, image, images, manageCommonData, manageNmmXml, mibDetail, mibFileNames,
		mibTree, nmmMappings, nmmXmlExist, obwiMappings, primaryMibTree, projectResources,
		resourceBundles, saveCustomCode, saveDatapoints, saveNMM, saveOBWI, saveSNMP, saveTraps,
		snmpMappings, trapsMappings, uploadCommonData, uploadCustomCode, uploadHelp, uploadImage,
		uploadMibFile, uploadNmmXml, uploadResourceBundle, uploadedFirmware, validateCustomCode,
		validateDatapoints, validateNMM, validateOBWI, validateSNMP, validateTraps
	}
	
	Set<LinkEntry> projectLinks(String projectName, String contextPath = '/'){
		[
			new LinkEntry(LinkKey.bundlesExist.toString(), "${contextPath}project/bundles/exist/$projectName"),
			new LinkEntry(LinkKey.customCodeMappings.toString(),  "${contextPath}project/customCode/$projectName"),
			new LinkEntry(LinkKey.datapointsMappings.toString(),  "${contextPath}project/datapoints/$projectName"),
			new LinkEntry(LinkKey.deleteCustomCode.toString(),  "${contextPath}project/customCode/$projectName"), 
			new LinkEntry(LinkKey.deleteMibFile.toString(),  "${contextPath}mib/$projectName"), 
			new LinkEntry(LinkKey.deleteProject.toString(),  "${contextPath}project/delete/$projectName"), 
			new LinkEntry(LinkKey.image.toString(),  "${contextPath}project/image/$projectName"), 
			new LinkEntry(LinkKey.images.toString(),  "${contextPath}project/images/$projectName"), 
			new LinkEntry(LinkKey.manageCommonData.toString(),  "${contextPath}project/nmmxml/common/$projectName"), 
			new LinkEntry(LinkKey.manageNmmXml.toString(),  "${contextPath}project/nmmxml/$projectName"), 
			new LinkEntry(LinkKey.mibDetail.toString(),  "${contextPath}mib/detail/$projectName"), 
			new LinkEntry(LinkKey.mibFileNames.toString(),  "${contextPath}mib/$projectName"), 
			new LinkEntry(LinkKey.mibTree.toString(),  "${contextPath}mib/$projectName"), 
			new LinkEntry(LinkKey.nmmMappings.toString(),  "${contextPath}project/nmm/$projectName"), 
			new LinkEntry(LinkKey.nmmXmlExist.toString(),  "${contextPath}project/nmmxml/exist/$projectName"), 
			new LinkEntry(LinkKey.obwiMappings.toString(),  "${contextPath}project/obwi/$projectName"), 
			new LinkEntry(LinkKey.primaryMibTree.toString(),  "${contextPath}mib/primary/$projectName"), 
			new LinkEntry(LinkKey.projectResources.toString(),  "${contextPath}project/resources/$projectName"), 
			new LinkEntry(LinkKey.resourceBundles.toString(),  "${contextPath}project/bundles/$projectName"), 
			new LinkEntry(LinkKey.saveCustomCode.toString(),  "${contextPath}project/save/customCode/$projectName"), 
			new LinkEntry(LinkKey.saveDatapoints.toString(),  "${contextPath}project/save/datapoints/$projectName"), 
			new LinkEntry(LinkKey.saveNMM.toString(),  "${contextPath}project/save/nmm/$projectName"), 
			new LinkEntry(LinkKey.saveOBWI.toString(),  "${contextPath}project/save/obwi/$projectName"), 
			new LinkEntry(LinkKey.saveSNMP.toString(),  "${contextPath}project/save/snmp/$projectName"), 
			new LinkEntry(LinkKey.saveTraps.toString(),  "${contextPath}project/save/traps/$projectName"), 
			new LinkEntry(LinkKey.snmpMappings.toString(),  "${contextPath}project/snmp/$projectName"), 
			new LinkEntry(LinkKey.trapsMappings.toString(),  "${contextPath}project/traps/$projectName"), 
			new LinkEntry(LinkKey.uploadCommonData.toString(),  "${contextPath}project/nmmxml/common"), 
			new LinkEntry(LinkKey.uploadCustomCode.toString(),  "${contextPath}project/customCode"), 
			new LinkEntry(LinkKey.uploadHelp.toString(),  "${contextPath}project/help"), 
			new LinkEntry(LinkKey.uploadImage.toString(),  "${contextPath}project/image"), 
			new LinkEntry(LinkKey.uploadMibFile.toString(),  "${contextPath}project/mibFile"), 
			new LinkEntry(LinkKey.uploadNmmXml.toString(),  "${contextPath}project/nmmxml"), 
			new LinkEntry(LinkKey.uploadResourceBundle.toString(),  "${contextPath}project/bundle"), 
			new LinkEntry(LinkKey.uploadedFirmware.toString(),  "${contextPath}project/firmware/$projectName"), 
			new LinkEntry(LinkKey.validateCustomCode.toString(),  "${contextPath}project/validate/customCode/$projectName"), 
			new LinkEntry(LinkKey.validateDatapoints.toString(),  "${contextPath}project/validate/datapoints/$projectName"), 
			new LinkEntry(LinkKey.validateNMM.toString(),  "${contextPath}project/validate/nmm/$projectName"), 
			new LinkEntry(LinkKey.validateOBWI.toString(),  "${contextPath}project/validate/obwi/$projectName"), 
			new LinkEntry(LinkKey.validateSNMP.toString(),  "${contextPath}project/validate/snmp/$projectName"), 
			new LinkEntry(LinkKey.validateTraps.toString(),  "${contextPath}project/validate/traps/$projectName")] as Set 
	}	
}

@Canonical
@EqualsAndHashCode(includes=['key'])
class LinkEntry implements Comparable<LinkEntry>{
	
	final String key
	final String value
	
	LinkEntry(String key, String value){
		this.key = key
		this.value = value
	}

	@Override
	public int compareTo(LinkEntry other) {
		this.key <=> other.key
	}
}

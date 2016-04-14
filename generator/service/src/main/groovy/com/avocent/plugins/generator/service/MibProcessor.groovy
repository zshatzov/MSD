package com.avocent.plugins.generator.service

import groovy.util.logging.Slf4j
import net.percederberg.mibble.MibLoader
import net.percederberg.mibble.MibType
import net.percederberg.mibble.MibValueSymbol
import net.percederberg.mibble.snmp.SnmpAgentCapabilities
import net.percederberg.mibble.snmp.SnmpModuleCompliance
import net.percederberg.mibble.snmp.SnmpModuleIdentity
import net.percederberg.mibble.snmp.SnmpNotificationGroup
import net.percederberg.mibble.snmp.SnmpNotificationType
import net.percederberg.mibble.snmp.SnmpObjectGroup
import net.percederberg.mibble.snmp.SnmpObjectIdentity
import net.percederberg.mibble.snmp.SnmpObjectType
import net.percederberg.mibble.snmp.SnmpTextualConvention
import net.percederberg.mibble.snmp.SnmpTrapType
import net.percederberg.mibble.type.ObjectIdentifierType
import net.percederberg.mibble.value.ObjectIdentifierValue

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

import com.avocent.plugins.generator.model.MibNode


@Service
@Scope('prototype')
@Slf4j('LOG')
class MibProcessor {	
	
	MibNode root
		
	def processMib( Reader input ){
		def loader = new MibLoader()
		def mib = loader.load(input)
		root = new MibNode(mib.name, null)

		def oids = []		
		mib.allSymbols.each {
			if(it instanceof MibValueSymbol){
				def value = ((MibValueSymbol) it).value
				if(value instanceof ObjectIdentifierValue){
					oids << (ObjectIdentifierValue) value
				}
			}
		}
				
		assert oids.size() > 0
		oids.each {
			addToTree(it)
		}
	}
 
	private MibNode addToTree(ObjectIdentifierValue oid){		 
		def parent	
			
		if(hasParent(oid)){			
			parent = addToTree(oid.parent)
		}else{
			parent = root
		}
		
		def node = parent.children.find{it.name == oid.symbol.name}
		if( node ){			
			//node already exists no need to add it
			return node 
		}
		
		node = new MibNode(oid.symbol.name, parent)
		node.oid = oid.toString()	
		node.mibDetail.oid = node.oid
		parent.addChild(node)
		
		def type = oid?.symbol?.type		
		if( type ){
			processMibType(type, node)
		}
		
		return node
	}	
	
	private boolean hasParent(ObjectIdentifierValue oid) {
		
		ObjectIdentifierValue  parent = oid?.parent
	
		return (oid?.getSymbol()?.getMib()
				&& parent?.getSymbol()?.getMib()
				&& parent.getSymbol().getMib().equals(oid.getSymbol().getMib()))
	}
	
	private def processMibType(MibType type, MibNode node) {
		switch( type ){
			case SnmpModuleIdentity:
				populateModuleDetails(type, node)
				break
			case SnmpObjectIdentity: 
			case SnmpObjectType:  
			case SnmpNotificationType: 
			case SnmpTrapType: 
			case SnmpTextualConvention: 
			case SnmpObjectGroup: 
			case SnmpNotificationGroup: 
			case SnmpModuleCompliance: 
			case SnmpAgentCapabilities:
				populateDetails( type, node )
				break	
			case ObjectIdentifierType:
				LOG.debug("Skipping object identifier type")
				break
			default:
				LOG.warn( "Type definition ({}) unknown", type )
				break		 
		}
	}
	
	private def populateModuleDetails(type, node){
		
		if(type.getClass().methods*.name.grep('getOrganization')){
			node.mibDetail.organization = type?.organization
		}
		
		if(type.getClass().methods*.name.grep('getDescription')){
			node.mibDetail.description = type?.description
		}
		
		if(type.getClass().methods*.name.grep('getLastUpdated')){
			node.mibDetail.lastUpdated = type?.lastUpdated
		}		
	}
	
	private def populateDetails(MibType type, MibNode node){
		
		if(type.getClass().methods*.name.grep('getAccess') 
				&& type['access']){		 
			node.mibDetail.access = type?.access
		}
		
		if(type.getClass().methods*.name.grep('getStatus')
				 && type['status']){			 
			node.mibDetail.status = type?.status
		}
		
		if(type.getClass().methods*.name.grep('getDescription') 
				&& type['description']){			
			node.mibDetail.description = type?.description?.replaceAll(/\s{2,}/, ' ')
		}
				
		if(type.getClass().methods*.name.grep('getSyntax')
				&& type['syntax']){
			node.mibDetail.syntax = type?.syntax
		}
	} 
}

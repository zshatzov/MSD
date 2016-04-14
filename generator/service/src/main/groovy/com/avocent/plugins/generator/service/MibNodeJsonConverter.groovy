package com.avocent.plugins.generator.service

import static com.mongodb.util.JSON.*
import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j

import org.springframework.stereotype.Service

import com.avocent.plugins.generator.model.MibNode
import com.avocent.plugins.generator.model.ProjectType
import com.avocent.plugins.generator.model.common.TrapsInformation
import com.mongodb.DBObject
import com.mongodb.util.JSON

/**
 * <p>
 * Service for converting a {@link com.avocent.plugins.generator.model.MibNode} to 
 * an instance of {@link com.mongodb.DBObjet}.
 * </p>
 * 
 * 
 * @author zshatzov
 *
 */
@Service
@Slf4j('LOG')
class MibNodeJsonConverter {	
	
	DBObject mibNodeToJson(MibNode tree, String webContext){
		
		LOG.debug("Converting MIB node ({}) to JSON", tree.name)
		
		def builder = new groovy.json.JsonBuilder()
		
		builder{
			text "${tree.name}"	
			state{
				opened true
			}		
			children owner.processChildren(builder, tree, webContext)			
		} 	
		
		return (parse(builder.toString()))
	}
	
	/**
	 * <p>
	 * 	An adapter method to convert a MIB Tree {@link com.mongodb.DBObject} to a
	 *  {@link com.avocent.plugins.generator.model.pdu.TrapsInformation} object.
	 *  The {@link com.mongodb.DBObject} represents a BSON serialized {@link MibNode} object. 
	 * </p>
	 *
	 * @param tree
	 * @param type
	 * @return
	 */
	TrapsInformation mibNodeToTrapsInformation(
			DBObject tree, ProjectType type ){
			
		def jsonObject = new JsonSlurper().parseText(JSON.serialize(tree))

		String pluginName = jsonObject.children[0]?.text
		LOG.debug("Traps plugin name=> $pluginName")

		String enterpriseOid = jsonObject.children[0]?.id
		LOG.debug("Traps Enterprise OID=> $enterpriseOid")

		String mibBase = jsonObject.children[0]?.id
		LOG.debug("Traps MIB base=> $mibBase")

		return new TrapsInformation( enterpriseOID: enterpriseOid,
				pluginName: pluginName, trapMIBBase: mibBase)
	}
	
	private def processChildren(builder, MibNode node, String webContext){
		LOG.debug("Processing children for node ({})", node.name)
	 
		node.children.collect{child->												
			builder{
				id child.oid
				text "${child.name}" 
				state{
					opened true
				}
				if(child.hasChildren()){
					children owner.processChildren(builder, child, webContext)
				}
			}
		}
	} 
}

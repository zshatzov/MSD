package com.avocent.plugins.generator.service

import groovy.util.logging.Slf4j

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

import com.avocent.plugins.generator.model.MibNode
import com.avocent.plugins.generator.model.MibTree

/**
 * <p>
 * A service to save a <em>MIB</em> detail for a single {@link com.avocent.plugins.generator.model.MibNode}
 * or a tree of nodes. 
 * </p>
 * 
 * @author zshatzov
 *
 */

@Service
@Scope('prototype')
@Slf4j('LOG')
class MibDetailProcessor {

	void process( MibNode tree, MibTree mibTree){
		
		LOG.debug("Process MIB node details for MIB tree ({0})", tree.name)
		
		tree.children.each{ node->
			processChildren( node, mibTree )
		}
	}
	
	
	private def processChildren(MibNode node, MibTree mibTree){
		LOG.debug("Processing children for node ({})", node.name)
	 
		if(node.mibDetailExists()){
			mibTree.addMibDetail(node.mibDetail)
		}	
			
		node.children.each{child-> 
			if(child.mibDetailExists()){
				mibTree.addMibDetail(child.mibDetail)
			}
			
			if(child.hasChildren()){
				owner.processChildren(child, mibTree)
			}
		}
	}
}

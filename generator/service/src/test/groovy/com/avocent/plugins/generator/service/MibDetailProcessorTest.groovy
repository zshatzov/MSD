package com.avocent.plugins.generator.service;

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

import com.avocent.plugins.generator.model.MibDetail
import com.avocent.plugins.generator.model.MibNode
import com.avocent.plugins.generator.model.MibTree
import com.avocent.plugins.generator.model.Project
import com.avocent.plugins.generator.model.ProjectStatus
import com.avocent.plugins.generator.model.ProjectType


class MibDetailProcessorTest { 
	
	MibNode parent
	MibTree mibTree
	
	@Before
	void before(){
		/**
		 * Define a simple tree structure:
		 *
		 *   Parent
		 *      |
		 *      +---- Child_1(oid: '1.0')
		 *               |
		 * 				 +---- Sub_Child_1(oid: '1.1')
		 * 				 |
		 *               +---- Sub_Child_2(oid: '1.2')
		 *
		 *   The corresponding json representation will be:
		 *   ===============================================
		 *   {"text": "Parent, "children": [{"text": "Child_1", "id": "1.0",
		 *    "children": [{"text": "Sub_Child_1", "id": "1.1"}, {"text": "Sub_Child_2", "id": "1.1"}]}]}
		 */
		
		parent = new MibNode('Parent')
				
		def child1 = new MibNode('Child_1')
		child1.oid = '1.0'
		
		def mibDetail = new MibDetail(oid: child1.oid, status: 'good', access: 'no-problem')
		child1.mibDetail = mibDetail
		
		def subChild1 = new MibNode('Sub_Child_1')
		subChild1.oid = '1.1'
		mibDetail = new MibDetail(oid: subChild1.oid, description: 'Wonderful Sub Child 1')
		subChild1.mibDetail = mibDetail
		
		def subChild2 = new MibNode('Sub_Child_2')
		subChild2.oid = '1.2'
		mibDetail = new MibDetail(oid: subChild2.oid, organization: 'Very Organized',
						lastUpdated: '201406251200Z')
		subChild2.mibDetail = mibDetail
		
		child1.children << subChild1
		child1.children << subChild2
		parent.children << child1
		
		mibTree = new MibTree(false)
	}
	
	@After
	void after(){
		parent = null
		mibTree = null
	}
	 
	@Test
	public void "Verfiy MIB detail count"() {	
			
		MibDetailProcessor processor = new MibDetailProcessor()
		processor.process(parent, mibTree)	
		
		assert 3 == mibTree.mibDetails.size() 
	}
	 
	
	@Test
	public void "Verify MIB detail with oid 1.1"() {
		MibDetailProcessor processor = new MibDetailProcessor()
		processor.process(parent, mibTree)
		
		def detail1_1 = mibTree.findMibDetail('1.1')
		assert detail1_1.description == 'Wonderful Sub Child 1'
	}
	
	@Test
	public void "Verify MIB detail with oid 1.2"() {
			
		MibDetailProcessor processor = new MibDetailProcessor()
		processor.process(parent, mibTree)
		
		def detail1_2 = mibTree.findMibDetail('1.2')
		assert detail1_2
		
		assert detail1_2.organization == 'Very Organized'
		assert detail1_2.lastUpdated == '201406251200Z'
	}
}

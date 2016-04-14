package com.avocent.plugins.generator.service

import org.junit.After
import org.junit.Before
import org.junit.Test

import com.avocent.plugins.generator.model.MibDetail
import com.avocent.plugins.generator.model.MibNode
import com.mongodb.DBObject

class MibNodeJsonConverterTest {
	
	MibNode parent

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
	}
	
	@After
	void after(){
		parent = null
	}
		
	
	@Test
	void verifyChildrenCount(){ 
		
		MibNodeJsonConverter converter = new MibNodeJsonConverter()
		DBObject result = converter.mibNodeToJson(parent, '/context')		
		 
		//Parent has one child
		assert 1 == result['children'].size()
				
		//Child_1 has two children
		assert 2 == result.children[0].children.size()
	}
	
	@Test
	 void verifyOids(){
		 
		 MibNodeJsonConverter converter = new MibNodeJsonConverter()
		 DBObject result = converter.mibNodeToJson(parent, '/context')
		 
		 //root  node has no oid
		 assert !result.id
		 
		 //Get Child_1
		 def child1 = result['children'][0]
		 println child1.getClass()		 
		 assert child1.id == '1.0'
		 
		 
		 def children = child1['children']		 
		 assert '1.1' == children[0].id
		 assert '1.2' == children[1].id
		 
	 }
}

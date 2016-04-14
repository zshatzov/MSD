package com.avocent.plugins.generator.service;

import static org.junit.Assert.*

import org.junit.Test
import org.springframework.core.io.ClassPathResource

class MibProcessorTest { 
	 
	@Test
	public void processSentry3MIB() {
		ClassPathResource resource = new ClassPathResource("Sentry3.mib")
		MibProcessor processor = new MibProcessor()
		processor.processMib(new FileReader(resource.file))
		
		def root = processor.root
		assert root 
		
		assert root.name == 'Sentry3-MIB'
		
		assert root.childrenCount() > 0
		
		def servertech = root.findChildByName("serverTech")
		assert servertech
		
		def sentry3 = servertech.findChildByName("sentry3")
		assert sentry3	
		
		assert sentry3.childrenCount() == 4
		
		def systemVersionMibDetail =  sentry3.findChildByName('systemGroup')?.findChildByName('systemVersion')?.mibDetail
		assert systemVersionMibDetail		
		
		assert systemVersionMibDetail.access == 'read-only'
		assert systemVersionMibDetail.status == 'current'
		assert systemVersionMibDetail.description == 'The firmware version of the system.'
		
	}
	
	@Test
	public void processSentry4MIB() {
		ClassPathResource resource = new ClassPathResource("Sentry4.mib")
		MibProcessor processor = new MibProcessor()
		processor.processMib(new FileReader(resource.file))
		
		def root = processor.root
		assert root
		
		assert root.name == 'Sentry4-MIB'
		
		assert root.childrenCount() > 0
		
		def servertech = root.findChildByName("serverTech")
		assert servertech
		
		def sentry4 = servertech.findChildByName("sentry4")
		assert sentry4
		
		assert sentry4.childrenCount() == 3
		
		def st4UnitObjectsGroupMibDetail =
		 	 sentry4.findChildByName('st4Conformance')?.findChildByName('st4Groups')?.findChildByName('st4UnitObjectsGroup').mibDetail
		assert st4UnitObjectsGroupMibDetail
			  
		assert st4UnitObjectsGroupMibDetail.status == 'current'
		assert st4UnitObjectsGroupMibDetail.description == 'Unit objects group.'		
	}
}

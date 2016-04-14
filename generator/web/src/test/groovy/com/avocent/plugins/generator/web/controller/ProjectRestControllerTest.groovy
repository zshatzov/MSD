package com.avocent.plugins.generator.web.controller

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*
import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder

import com.avocent.plugins.generator.config.RootConfig
import com.avocent.plugins.generator.dao.ProjectRepository
import com.avocent.plugins.generator.model.MibTree
import com.avocent.plugins.generator.model.Project
import com.avocent.plugins.generator.model.ProjectStatus
import com.avocent.plugins.generator.model.ProjectType
import com.avocent.plugins.generator.model.common.TrapsInformation
import com.avocent.plugins.generator.model.view.ElementViewComponent
import com.avocent.plugins.generator.model.view.GroupViewComponent
import com.avocent.plugins.generator.model.view.MetadataViewComponent
import com.avocent.plugins.generator.service.DeviceViewService
import com.avocent.plugins.generator.service.MibDetailProcessor
import com.avocent.plugins.generator.service.MibNodeJsonConverter
import com.avocent.plugins.generator.service.MibProcessor
import com.avocent.plugins.generator.service.ProjectPackagingService
import com.avocent.plugins.generator.service.ProjectService
import com.avocent.plugins.generator.service.XMLFileGeneratorService
import com.avocent.plugins.generator.web.ProjectResource

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = [ProjectRestControllerTestConfig, RootConfig])
@WebAppConfiguration
class ProjectRestControllerTest {
		
	MockMvc mockMvc
	
	@Mock UriComponentsBuilder ucb
	
	@Mock UriComponents uc
	
	@Mock
	MibProcessor mibProcessor
	
	@Mock
	MibDetailProcessor mibDetailProcessor
	
	@Mock
	DeviceViewService deviceViewService
	
	@Mock
	MibNodeJsonConverter converter
	
	@Mock
	XMLFileGeneratorService fileGenerator
	
	@Mock
	ProjectPackagingService projectPackagingService
	
	@Mock
	ProjectService projectService
	
	@Mock
	ResourceBundleMessageSource messageSoruce
	
	@Mock
	ServletContext servletContext
	
	@Mock
	ResourceBundleMessageSource messageSource	

	@Mock
	HttpServletRequest httpServletRequest
	
	@Mock
	org.apache.tools.ant.Project antProject
	
	@Autowired
	ProjectRepository projectRepository
		
	@Autowired @InjectMocks
	ProjectRestController controller
	
	ClassPathResource mibFile = new ClassPathResource("Sentry3.mib")
	
	def datapointMappings = [new GroupViewComponent(group: "PDUInformation",
		label: "PDU Info", elements : [
			new ElementViewComponent(name: 'id', label: 'Identification',
				metadata: new MetadataViewComponent(required: true))
	])]
		
	@Before
	void initMocks(){
		MockitoAnnotations.initMocks(this)
		when(uc.toUri()).thenReturn('http://localhost:8080/'.toURI())		
		when(ucb.path(anyString())).thenReturn(ucb)
		when(ucb.build()).thenReturn(uc)
	}
	 
	@Test
	void createOk() {
		
		def part = [
			getInputStream: { mibFile.inputStream },
			getSubmittedFileName: { 'Sentry3.mib' }
		] as javax.servlet.http.Part
	
		TrapsInformation traps = new TrapsInformation(pluginName: 'Sentry4-MIB_PDU',
			trapMIBBase: 'ServerTech', enterpriseOID: '1.3.6.1.4.1.1718')
				
		MibNodeJsonConverter mockConverter = [
			mibTreeToTrapsInformation: {mibTree, type-> return traps}			
		] as MibNodeJsonConverter
		controller.converter = mockConverter
		
		ProjectPackagingService mockProjectPackagingService = [
			saveMibFile: {String projectName, String mibFileName, input, servletCtx, primary->
				 return new MibTree(mibTreeObject: new com.mongodb.BasicDBObject())
			},
			createProjectStructure: {String projectName-> }
		] as ProjectPackagingService
		controller.projectPackagingService = mockProjectPackagingService
		
		when(projectService.validateProjectWithNameNotExists(anyString())).thenReturn(true)	
	
		ProjectResource resp = controller.create(part, 'TestProject', 
				ProjectType.PDU, ucb)

				assert resp
		
		assert resp.name == 'TestProject'
		assert resp.type == ProjectType.PDU
		assert resp.status == ProjectStatus.PARTIAL
	
		assert resp.links*.key.containsAll(['mibTree', 'saveDatapoints', 'deleteProject', 'mibDetail'])
		
		assert resp.links*.value.containsAll([
			"http://localhost:8080/mib/TestProject",
			"http://localhost:8080/project/save/datapoints/TestProject",
			"http://localhost:8080/project/delete/TestProject",
			"http://localhost:8080/mib/detail/TestProject"	
		])
	}

	@Test
	void deleteOk() {
		Project project = new Project('Sentry3')
		project.status = ProjectStatus.PARTIAL
		project.type = ProjectType.PDU
		
		when(projectRepository.findByName(anyString())).thenReturn(project)
		
		controller.delete(project.name)	 
		
		//verify ProjectService#deleteProejct() was called once
		verify(projectService).deleteProject(project.name)	
	}
	
	@Test
	void openOk(){
		
		Project project = new Project('Sentry3')
		project.status = ProjectStatus.PARTIAL
		project.type = ProjectType.PDU
		
		project.dataPointMapping = datapointMappings
		
		when(projectService.getProjectByName(anyString())).thenReturn(project)		
		
		ProjectResource resp = controller.open(project.name, ucb)
		
		assert resp
		
		assert resp.name == project.name
		
		assert resp.links*.key.containsAll(['mibTree', 'datapointsMappings', 'saveDatapoints', 'deleteProject', 'mibDetail', 
				'trapsMappings', 'obwiMappings', 'snmpMappings', 'nmmMappings'])
		
		assert resp.links*.value.containsAll([
			"http://localhost:8080/mib/Sentry3",
			"http://localhost:8080/project/datapoints/Sentry3",
			"http://localhost:8080/project/save/datapoints/Sentry3",
			"http://localhost:8080/project/delete/Sentry3",
			"http://localhost:8080/mib/detail/Sentry3",
			"http://localhost:8080/project/traps/Sentry3",
			"http://localhost:8080/project/obwi/Sentry3",
			"http://localhost:8080/project/snmp/Sentry3",
			"http://localhost:8080/project/nmm/Sentry3"
		])		
	}
	
	@Test
	void saveDatapointsOk(){
		
		Project project = new Project('Sentry3')
		project.status = ProjectStatus.PARTIAL
		project.type = ProjectType.PDU		

		when(projectService.getProjectByName(anyString())).thenReturn(project)
		
		controller.save(project.name, datapointMappings)
		
		//verify ProjectRepsitory#findByName() was called once
		verify(projectService).getProjectByName(project.name)
		//verify ProjectRepsitory#save() was called once
		verify(projectService).saveProject(project)			
	}
}

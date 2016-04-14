package com.avocent.plugins.generator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.avocent.plugins.generator.model.DeviceView;
import com.avocent.plugins.generator.service.DeviceViewService;
import com.avocent.plugins.generator.service.ProjectPackagingService;

@SpringBootApplication
@EnableMongoRepositories("com.avocent.plugins.generator.dao")
@EnableWebMvc
public class PluginsGeneratorApplication implements CommandLineRunner{
	
	private static final Logger LOG = LoggerFactory.getLogger(PluginsGeneratorApplication.class); 	

	@Autowired
	private DeviceViewService deviceViewService;
	
	@Autowired
	private MongoTemplate mongoTemplate;	
	
	@Autowired 
	private ProjectPackagingService projectPackagingService;
	
	@Autowired
	private Environment env;
	
	public static void main(String[] args) {		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(PluginsGeneratorApplication.class);		
		SpringApplication app = builder.application();
		LOG.info("Starting plugins generator application...");
		app.run(args);
	} 

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Initialize the views collection if needed");
		List<DeviceView> views = deviceViewService.retrieveAll();
		Query query = Query.query(Criteria.where("projectType").exists(true));				
		if( mongoTemplate.count(query, "views") < views.size() ){
			// remove all documents
			mongoTemplate.getDb().command("db.views.remove({})");
			// re-populate collection
			views.stream().forEach(view-> mongoTemplate.save(view)); 
		} 		
	}
}

package com.avocent.plugins.generator.web.controller;

import static org.mockito.Mockito.mock;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.avocent.plugins.generator.dao.DeviceViewRepository;
import com.avocent.plugins.generator.dao.ProjectRepository;

@EnableAutoConfiguration
@ComponentScan(basePackages= "com.avocent.plugins.generator.service, com.avocent.plugins.generator.web.controller" )
@Configuration
public class ProjectRestControllerTestConfig { 

	@Bean
	public DeviceViewRepository deviceViewRespository(){
		return mock(DeviceViewRepository.class);
	}
	
	@Bean
	public ProjectRepository projectRespository(){
		return mock(ProjectRepository.class);
	}
	
	@Bean(name="JSR303Validator")
    public javax.validation.Validator getJSR303Validator() {		
	    return  mock(javax.validation.Validator.class);
	}
}

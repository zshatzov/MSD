package com.avocent.plugins.generator.web.controller;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.http.Part;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.avocent.plugins.generator.SeedPluginFileUploadException;

/**
 * <p>
 *  A <em>Rest</em> service used to upload a <code>Seed Plugin</code> zip
 *  file. The <code>Seed Plugin</code> contains the java libraries required
 *  for the custom plugin to be successfully deployed in a <em>DSView</em>
 *  system.
 * </p> 
 * 
 * @author zshatzov
 *
 */
@RestController
@RequestMapping("/config")
public class ConfigurationRestController extends BaseRestController{
		
	@RequestMapping(value = "/install", method = RequestMethod.POST, 
			consumes = { MULTIPART_FORM_DATA_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public void uploadSeedPlugin(@RequestPart("seedPlugin") Part seedPlugin) {

		LOG.debug("Upload seed plugin file with name {}",
				seedPlugin.getSubmittedFileName());	
		
		Path seedPluginPath = projectPackagingService.getTempBaseDirPath().resolve(
				 seedPlugin.getSubmittedFileName());
		try(InputStream zipInputStream = seedPlugin.getInputStream()){			
			Files.deleteIfExists(seedPluginPath);			
			Path tempFile = Files.createFile(seedPluginPath);			
			Files.copy(seedPlugin.getInputStream(), tempFile, REPLACE_EXISTING);
			projectPackagingService.unzipSeedPlugin(tempFile);			
		} catch (IOException e) {
			LOG.error("Failed to upload seed plugin file", e);
			throw new SeedPluginFileUploadException(e);
		} finally{
			projectPackagingService.deleteFile(seedPluginPath);
		}
	}
}

package com.avocent.plugins.generator.web;

import static java.util.EnumSet.of;

import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.avocent.plugins.generator.config.RootConfig;

public class PluginsGeneratorWebappInitializer extends
		SpringServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> webAppInitializerClasses,
			ServletContext servletContext) throws ServletException {
		/**
		 * Create the 'root' Spring application context *
		 */
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootConfig.class);

		/**
		 * Manage the lifecycle of the root application context
		 */
		servletContext.addListener(new ContextLoaderListener(rootContext));

		/**
		 * Add filter to handle HTTP Put/Delete methods
		 */
		FilterRegistration.Dynamic hiddenFilter = servletContext.addFilter(
				"HiddenHttpMethodFilter",
				org.springframework.web.filter.HiddenHttpMethodFilter.class);
		hiddenFilter.addMappingForServletNames(of(DispatcherType.REQUEST),
				true, "dispatcher");

		/**
		 * Create the dispatcher servlet's Spring application context
		 */
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebConfig.class);

		/**
		 * Register and map the dispatcher servlet
		 */
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		super.onStartup(webAppInitializerClasses, servletContext);
	}
}

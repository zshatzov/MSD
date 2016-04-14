package com.avocent.plugins.generator.web.filter

import groovy.util.logging.Slf4j

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletResponse

import org.springframework.stereotype.Component

@Component
@Slf4j('LOG')
@WebFilter(filterName="CORSFilter", urlPatterns=['/project/*', '/mib/*', '/config/*', '/device/*'])
class SimpleCORSFilter implements Filter {

	@Override
	void doFilter(ServletRequest request, ServletResponse response,	FilterChain chain) {
		
		LOG.debug("Setting request headers to handle CORS clients")
		['Access-Control-Allow-Origin': '*',
		 'Access-Control-Allow-Methods' : 'POST, GET, PUT, OPTIONS, DELETE',
		 'Access-Control-Max-Age': '3600',
		 'Access-Control-Allow-Headers': 'x-requested-with, Content-Type, Content-Range, Content-Disposition, Content-Description'].each{k,v->
		 	(response as HttpServletResponse).setHeader(k, v)
		}
		
		chain.doFilter(request, response)
	} 

	@Override
	void init(FilterConfig filterConfig){		
	}

	@Override
	public void destroy() {		
	}
}

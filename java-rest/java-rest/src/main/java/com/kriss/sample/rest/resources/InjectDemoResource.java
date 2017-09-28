package com.kriss.sample.rest.resources;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.kriss.sample.rest.resources.beans.InjectDemoFilterBean;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces({MediaType.TEXT_PLAIN})
public class InjectDemoResource {

	@GET
	@Path("/annotations")
	public String getParamList(@MatrixParam("param") String value,
								@HeaderParam("customHeaderParam") String header,
								@CookieParam("cookie") String cookie) {
		return "Matrix Param: " + value + " Header: " + header + " Cookie: " + cookie;
	}
	
	@GET
	@Path("/context")
	public String getContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		System.out.println(uriInfo.getPathParameters());
		System.out.println(uriInfo.getPathSegments());
		System.out.println(uriInfo.getQueryParameters());
		
		System.out.println(headers.getLength());
		System.out.println(headers.getCookies());
		System.out.println(headers.getMediaType());
		System.out.println(headers.getDate());
		System.out.println(headers.getLanguage());
		System.out.println(headers.getHeaderString("customheader"));
		System.out.println(headers.getRequestHeader("customheader"));
		System.out.println(headers.getRequestHeaders());
		System.out.println(headers.getAcceptableLanguages());
		System.out.println(headers.getAcceptableMediaTypes());
		
		return uriInfo.getAbsolutePath().getPath();
	}
	
	@GET
	@Path("/beanparams")
	public String getBeanParams(@BeanParam InjectDemoFilterBean params) {
		return "Matrix Param: " + params.getValue() + " Header: " + params.getHeader() + 
				" Cookie: " + params.getCookie();
	}
}

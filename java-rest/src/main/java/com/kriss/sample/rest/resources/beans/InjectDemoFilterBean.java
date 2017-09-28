package com.kriss.sample.rest.resources.beans;

import javax.ws.rs.CookieParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;

public class InjectDemoFilterBean {

	private @MatrixParam("param") String value;
	private @HeaderParam("customHeaderParam") String header;
	private @CookieParam("cookie") String cookie;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
}

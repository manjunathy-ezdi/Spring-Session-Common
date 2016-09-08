package com.ezdi.redisserializer.springsecurity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class EzWebAuthenticationDetails extends WebAuthenticationDetails {

	public EzWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
	}
	
	public EzWebAuthenticationDetails(){
		this(new EzDummyHttpServletRequest());
	}
	
	private String remoteAddress;
	private String sessionId;
	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}

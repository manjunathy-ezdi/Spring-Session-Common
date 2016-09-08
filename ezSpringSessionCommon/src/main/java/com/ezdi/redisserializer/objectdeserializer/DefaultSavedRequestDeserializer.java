package com.ezdi.redisserializer.objectdeserializer;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.security.web.PortResolver;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import com.ezdi.redisserializer.SerializerConstants;
import com.ezdi.redisserializer.springsecurity.EzDummyHttpServletRequest;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/*
 *  * {"@class":"org.springframework.security.web.savedrequest.DefaultSavedRequest",
 *  "cookies":["java.util.ArrayList",[
 *  				{"@class":"javax.servlet.http.Cookie",
 *  				 "name":"JSESSIONID",
 *  				 "value":"179D19DEC71A11DCE41B9BAF2B845113",
 *  				 "version":0,
 *  				 "comment":null,
 *  				 "domain":null,
 *  				 "maxAge":-1,
 *  				 "path":null,
 *  				 "secure":false,
 *  				 "httpOnly":false}]],
 *  "locales":["java.util.ArrayList",["en_IN"]],
 *  "contextPath":"",
 *  "method":"GET",
 *  "pathInfo":null,
 *  "queryString":null,
 *  "requestURI":"/explicitLogout",
 *  "requestURL":"http://localhost:9090/explicitLogout",
 *  "scheme":"http",
 *  "serverName":"localhost",
 *  "servletPath":"/explicitLogout",
 *  "serverPort":9090,
 *  "redirectUrl":"http://localhost:9090/explicitLogout",
 *  "parameterNames":["java.util.TreeMap$KeySet",[]],
 *  "parameterMap":{"@class":"java.util.TreeMap"},
 *  "headerNames":["java.util.TreeMap$KeySet",
 *  				 ["connection","cookie",
 *  				  "host","user-agent"]]} 
 *  :: No suitable constructor found for type [simple type, class org.springframework.security.web.savedrequest.DefaultSavedRequest]: can not instantiate from JSON object (missing default constructor or creator, or perhaps need to add/enable type information?)
 */

public class DefaultSavedRequestDeserializer extends JsonDeserializer<DefaultSavedRequest>{

	@Override
	public DefaultSavedRequest deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		
		ObjectCodec oc = jsonParser.getCodec();
		JsonFactory jFactory = new JsonFactory(oc);
		JsonNode node = oc.readTree(jsonParser);
		if(node != null){
			EzDummyHttpServletRequest request = new EzDummyHttpServletRequest();
			PortResolver portResolver = new PortResolverImpl();
			JsonNode cookiesNode = node.get(SerializerConstants.COOKIES_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			JsonNode localesNode = node.get(SerializerConstants.LOCALES_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			JsonNode contextPathNode = node.get(SerializerConstants.CONTEXTPATH_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			JsonNode methodNode = node.get(SerializerConstants.METHOD_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			JsonNode pathInfoNode = node.get(SerializerConstants.PATHINFO_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			JsonNode queryStringNode = node.get(SerializerConstants.COOKIES_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			JsonNode requestURINode = node.get(SerializerConstants.REQUESTURI_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			JsonNode requestURLNode = node.get(SerializerConstants.REQUESTURL_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			JsonNode schemeNode = node.get(SerializerConstants.SCHEME_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			JsonNode serverNameNode = node.get(SerializerConstants.SERVERNAME_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			JsonNode servletPathNode = node.get(SerializerConstants.SERVLETPATH_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			JsonNode serverPortNode = node.get(SerializerConstants.SERVERPORT_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			JsonNode parameterMapNode = node.get(SerializerConstants.PARAMETERMAP_SPRING_SECURITY_DEFAULTSAVEDREQUEST_JSON_KEY);
			
			if(parameterMapNode != null){
				TypeReference<Map<String,String[]>> typeRefParameterMap 
					= new TypeReference<Map<String, String[]>>() {};
				Map<String, String[]> parameterMap 
					= oc.readValue(jFactory.createParser(parameterMapNode.toString()), typeRefParameterMap);
				request.setParameterMap(parameterMap);
			}
			
			if(serverPortNode != null){
				request.setServerPort(serverPortNode.asInt());
			}
			
			if(servletPathNode != null){
				request.setServletPath(servletPathNode.asText());
			}
			
			if(serverNameNode != null){
				request.setServerName(serverNameNode.asText());
			}
			
			if(schemeNode != null){
				request.setScheme(schemeNode.asText());
			}
			
			if(requestURLNode != null){
				request.setRequestURL(new StringBuffer(requestURLNode.asText()));
			}
			
			if(requestURINode != null){
				request.setRequestURI(requestURINode.asText());
			}
			
			if(queryStringNode != null){
				request.setQueryString(queryStringNode.asText());
			}
			
			if(pathInfoNode != null){
				request.setPathInfo(pathInfoNode.asText());
			}
			
			if(methodNode != null){
				request.setMethod(methodNode.asText());
			}
			
			if(contextPathNode != null){
				request.setContextPath(contextPathNode.asText());
			}
			
			if(cookiesNode != null && jFactory != null){
				TypeReference<Cookie[]> typeRefCookieArr = new TypeReference<Cookie[]>(){};
				Cookie[] cookies = oc.readValue(jFactory.createParser(cookiesNode.toString()),typeRefCookieArr);
				request.setCookies(cookies);
			}
			
			if(localesNode != null && jFactory != null){
				TypeReference<List<Locale>> typeRefLocales = new TypeReference<List<Locale>>(){};
				List<Locale> locales = oc.readValue(jFactory.createParser(localesNode.toString()), typeRefLocales);
				request.setLocales(locales);
			}
						
			DefaultSavedRequest defaultSavedRequest = new DefaultSavedRequest(request, portResolver);
			return defaultSavedRequest;
		}
		return null;
	}

}

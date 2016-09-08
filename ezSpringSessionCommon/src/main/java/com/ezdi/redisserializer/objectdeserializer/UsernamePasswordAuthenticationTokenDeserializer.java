package com.ezdi.redisserializer.objectdeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.ezdi.redisserializer.SerializerConstants;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/*
 * {\"@class\":\"org.springframework.security.core.context.SecurityContextImpl\",
 *  \"authentication\":{\"@class\":\"org.springframework.security.authentication.UsernamePasswordAuthenticationToken\",
 *  					\"details\":{\"@class\":\"org.springframework.security.web.authentication.WebAuthenticationDetails\",
 *  								 \"remoteAddress\":\"127.0.0.1\",
 *  								 \"sessionId\":null},
 *  					\"authorities\":[\"java.util.ArrayList\",
 *  									[{\"@class\":\"org.springframework.security.core.authority.SimpleGrantedAuthority\",
 *  									  \"authority\":\"ROLE_1_ADMIN\"}]],
 *  					\"authenticated\":true,
 *  					\"principal\":{\"@class\":\"org.springframework.security.core.userdetails.User\",
 *  								   \"password\":null,
 *  								   \"username\":\"trump\",
 *  								   \"authorities\":[\"java.util.Collections$UnmodifiableSet\",
 *  												   [{\"@class\":\"org.springframework.security.core.authority.SimpleGrantedAuthority\",
 *  													 \"authority\":\"ROLE_1_ADMIN\"}]],
 *  								   \"accountNonExpired\":true,
 *  								   \"accountNonLocked\":true,
 *  								   \"credentialsNonExpired\":true,
 *  								   \"enabled\":true},
 *  								   \"credentials\":null,
 *  								   \"name\":\"trump\"}}
 */
public class UsernamePasswordAuthenticationTokenDeserializer extends JsonDeserializer<org.springframework.security.authentication.UsernamePasswordAuthenticationToken>{

	@Override
	public org.springframework.security.authentication.UsernamePasswordAuthenticationToken deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonFactory jFactory = new JsonFactory(oc);
		JsonNode node = oc.readTree(jsonParser);
		if(node != null){
			JsonNode authoritiesNode = node.get(SerializerConstants.AUTHORITIES_SPRING_SECURITY_AUTHENTICATION_JSON_KEY);
			JsonNode detailsNode = node.get(SerializerConstants.DETAILS_SPRING_SECURITY_AUTHENTICATION_JSON_KEY);
			JsonNode principalNode = node.get(SerializerConstants.PRINCIPAL_SPRING_SECURITY_AUTHENTICATION_JSON_KEY);
			JsonNode authenticatedNode = node.get(SerializerConstants.AUTHENTICATED_SPRING_SECURITY_AUTHENTICATION_JSON_KEY); 
			
			List<GrantedAuthority> authList = extractGrantedAuthorityList(oc, authoritiesNode, jFactory);
			UserDetails principal = extractPrincipal(oc, principalNode, jFactory); 
			String credentials = null;
			boolean authenticated = extractAuthenticatedNode(authenticatedNode);
			UsernamePasswordAuthenticationToken authentication = null;
			
			if(authenticated){
				authentication = new UsernamePasswordAuthenticationToken(
					principal, credentials, authList);
			}
			else{
				authentication = new UsernamePasswordAuthenticationToken(
						principal, credentials);
			}
			if(authentication != null){
				WebAuthenticationDetails details = extractWebAuthenticationDetails(oc, detailsNode, jFactory);
				if(details != null) authentication.setDetails(details);
			}
			return authentication;
		}
		return null;
	}
	
	private List<GrantedAuthority> 
		extractGrantedAuthorityList(ObjectCodec oc, JsonNode authoritiesNode, JsonFactory jsonFactory)
			throws JsonParseException, JsonProcessingException, IOException{
		if(authoritiesNode != null && jsonFactory != null){
			TypeReference<ArrayList<SimpleGrantedAuthority>> typeRefAuthList 
			= new TypeReference<ArrayList<SimpleGrantedAuthority>>() {};
			return oc.readValue(jsonFactory.createParser(authoritiesNode.toString()), typeRefAuthList); 
		}
		return null;
	}
	
	private WebAuthenticationDetails extractWebAuthenticationDetails (ObjectCodec oc, 
			JsonNode detailsNode, JsonFactory jFactory)
			throws JsonParseException, JsonProcessingException, IOException{
		if(detailsNode != null){
			TypeReference<WebAuthenticationDetails> typeRefWebAuth 
			= new TypeReference<WebAuthenticationDetails>() {};
			return oc.readValue(jFactory.createParser(detailsNode.toString()),typeRefWebAuth);
		}
		return null;
	}
	
	private User extractPrincipal (ObjectCodec oc, JsonNode principalNode, JsonFactory jFactory)
		throws JsonParseException, JsonProcessingException, IOException{
		if(principalNode != null && jFactory != null){
			TypeReference<org.springframework.security.core.userdetails.UserDetails> typeRefUser = 
					new TypeReference<org.springframework.security.core.userdetails.UserDetails>() {};
			return oc.readValue(jFactory.createParser(principalNode.toString()), typeRefUser);		
		}
		return null;
	}
	
	private boolean extractAuthenticatedNode(JsonNode authenticatedNode){
		if(authenticatedNode != null){
			return authenticatedNode.asBoolean();
		}
		return false;
	}
}

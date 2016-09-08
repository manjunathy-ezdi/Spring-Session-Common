package com.ezdi.redisserializer.objectdeserializer;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ezdi.redisserializer.SerializerConstants;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/*
 * \"principal\":{\"@class\":\"org.springframework.security.core.userdetails.User\",
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
 *  								   \"name\":\"trump\"}
 */

public class UserDeserializer extends JsonDeserializer<org.springframework.security.core.userdetails.User>{

	@Override
	public org.springframework.security.core.userdetails.User deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonFactory jFactory = new JsonFactory(oc);
		JsonNode node = oc.readTree(jsonParser);
		if(node != null){
			TypeReference<Collection<SimpleGrantedAuthority>> typeRefAuthSet = new TypeReference<Collection<SimpleGrantedAuthority>>() {};
			JsonNode authoritiesNode = node.get(SerializerConstants.AUTHORITIES_SPRING_SECURITY_USER_JSON_KEY);
			Collection<GrantedAuthority> authSet = null;
			String username=null;
			String password=null;
			boolean accountNonExpired=true;
			boolean accountNonLocked = true;
			boolean credentialsNonExpired = true;
			boolean enabled = true;
			if(authoritiesNode != null && jFactory != null){
				authSet = oc.readValue(jFactory.createParser(authoritiesNode.toString()), typeRefAuthSet);
			}
			JsonNode usernameNode = node.get(SerializerConstants.USERNAME_SPRING_SECURITY_USER_JSON_KEY); 
			if(usernameNode!= null){
				username = usernameNode.asText();
			}
			JsonNode passwordNode = node.get(SerializerConstants.PASSWORD_SPRING_SECURITY_USER_JSON_KEY);
			if(passwordNode != null) {
				password = passwordNode.asText();
			}
			JsonNode accountNonExpiredNode = node.get(SerializerConstants.ACCOUNT_NON_EXPIRED_SPRING_SECURITY_USER_JSON_KEY); 
			if(accountNonExpiredNode != null){
				accountNonExpired = accountNonExpiredNode.asBoolean();
			}
			JsonNode accountNonLockedNode = node.get(SerializerConstants.ACCOUNT_NON_LOCKED_SPRING_SECURITY_USER_JSON_KEY);
			if(accountNonLockedNode != null){
				accountNonLocked = accountNonLockedNode.asBoolean();
			}
			JsonNode credentialsNonExpiredNode = node.get(SerializerConstants.CREDENTIALS_NON_EXPIRED_SPRING_SECURITY_USER_JSON_KEY);
			if(credentialsNonExpiredNode != null){
				credentialsNonExpired = credentialsNonExpiredNode.asBoolean();
			}
			JsonNode enabledNode = node.get(SerializerConstants.ENABLED_SPRING_SECURITY_USER_JSON_KEY);
			if(enabledNode != null){
				enabled = enabledNode.asBoolean();
			}
			org.springframework.security.core.userdetails.User user = 
					new org.springframework.security.core.userdetails.User(username,password,
							enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,authSet);
			return user;
		}
		return null;
	}
	
}


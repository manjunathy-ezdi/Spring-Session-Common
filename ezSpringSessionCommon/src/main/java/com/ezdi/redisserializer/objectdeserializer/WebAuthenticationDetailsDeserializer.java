package com.ezdi.redisserializer.objectdeserializer;

import java.io.IOException;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.ezdi.redisserializer.SerializerConstants;
import com.ezdi.redisserializer.springsecurity.EzWebAuthenticationDetails;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/*
 * \"details\":{\"@class\":\"org.springframework.security.web.authentication.WebAuthenticationDetails\",
 *  								 \"remoteAddress\":\"127.0.0.1\",
 *  								 \"sessionId\":null}
 */

public class WebAuthenticationDetailsDeserializer extends JsonDeserializer<WebAuthenticationDetails>{

	@Override
	public WebAuthenticationDetails deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		EzWebAuthenticationDetails details = new EzWebAuthenticationDetails();
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		if(node != null){
			JsonNode remoteAddressNode = node.get(SerializerConstants.REMOTE_ADDRESS_SPRING_SECURITY_WEBAUTHENTICATION_JSON_KEY);
			JsonNode sessionIdNode = node.get(SerializerConstants.SESSION_ID_SPRING_SECURITY_WEBAUTHENTICATION_JSON_KEY); 
			if(remoteAddressNode != null){
				details.setRemoteAddress(remoteAddressNode.asText());
			}
			if(sessionIdNode != null){
				details.setSessionId(sessionIdNode.asText());
			}
		}		
		return details;
	}

}

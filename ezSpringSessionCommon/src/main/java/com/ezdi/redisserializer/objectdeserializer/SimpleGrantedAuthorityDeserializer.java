package com.ezdi.redisserializer.objectdeserializer;

import java.io.IOException;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ezdi.redisserializer.SerializerConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class SimpleGrantedAuthorityDeserializer extends JsonDeserializer<SimpleGrantedAuthority>{

	@Override
	public SimpleGrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		if(node != null){
			JsonNode authorityNode = node.get(SerializerConstants.AUTHORITY_SPRING_SECURITY_USER_JSON_KEY); 
			if(authorityNode!=null){
				String authority = authorityNode.asText();
				if(authority != null && !authority.isEmpty()){
					return new SimpleGrantedAuthority(authority);
				}
			}			
		}
		return null;
	}
	
	

}

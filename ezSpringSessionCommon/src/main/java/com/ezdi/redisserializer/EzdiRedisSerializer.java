package com.ezdi.redisserializer;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import javax.security.auth.login.CredentialExpiredException;
import javax.servlet.http.Cookie;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

import com.ezdi.redisserializer.mixin.AuthenticationExceptionMixIn;
import com.ezdi.redisserializer.mixin.AuthenticationServiceExceptionMixIn;
import com.ezdi.redisserializer.mixin.BadCredentialsExceptionMixIn;
import com.ezdi.redisserializer.mixin.CookieMixIn;
import com.ezdi.redisserializer.mixin.CredentialsExpiredExceptionMixIn;
import com.ezdi.redisserializer.mixin.DisabledExceptionMixIn;
import com.ezdi.redisserializer.mixin.LockedExceptionMixIn;
import com.ezdi.redisserializer.mixin.UnmodifiableSetMixin;
import com.ezdi.redisserializer.mixin.UsernameNotFoundExceptionMixIn;
import com.ezdi.redisserializer.objectdeserializer.DefaultSavedRequestDeserializer;
import com.ezdi.redisserializer.objectdeserializer.SimpleGrantedAuthorityDeserializer;
import com.ezdi.redisserializer.objectdeserializer.UserDeserializer;
import com.ezdi.redisserializer.objectdeserializer.UsernamePasswordAuthenticationTokenDeserializer;
import com.ezdi.redisserializer.objectdeserializer.WebAuthenticationDetailsDeserializer;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component("springSessionDefaultRedisSerializer")
public class EzdiRedisSerializer implements RedisSerializer<Object> {

	public EzdiRedisSerializer(){
		init();
	}
	
	private ObjectMapper createObjectMapper(){
		ObjectMapper ret = new ObjectMapper();
		ret.enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
		SimpleModule module = new SimpleModule();
		module.addDeserializer(SimpleGrantedAuthority.class, new SimpleGrantedAuthorityDeserializer());
		module.addDeserializer(org.springframework.security.core.userdetails.User.class, new UserDeserializer());
		module.addDeserializer(UsernamePasswordAuthenticationToken.class,
				new UsernamePasswordAuthenticationTokenDeserializer());
		module.addDeserializer(WebAuthenticationDetails.class, new WebAuthenticationDetailsDeserializer());
		module.addDeserializer(DefaultSavedRequest.class, new DefaultSavedRequestDeserializer());
		ret.registerModule(module);
		ret.addMixIn(Collections.unmodifiableSet(Collections.EMPTY_SET).getClass(), UnmodifiableSetMixin.class);
		ret.addMixIn(Cookie.class, CookieMixIn.class);
		ret.addMixIn(UsernameNotFoundException.class, UsernameNotFoundExceptionMixIn.class);
		ret.addMixIn(LockedException.class, LockedExceptionMixIn.class);
		ret.addMixIn(DisabledException.class, DisabledExceptionMixIn.class);
		ret.addMixIn(CredentialsExpiredException.class, CredentialsExpiredExceptionMixIn.class);
		ret.addMixIn(BadCredentialsException.class, BadCredentialsExceptionMixIn.class);
		ret.addMixIn(AuthenticationException.class, AuthenticationExceptionMixIn.class);
		ret.addMixIn(AuthenticationServiceException.class, AuthenticationServiceExceptionMixIn.class);
		return ret;
	}
	
	private void init(){
		om = createObjectMapper();
	}
	
	private ObjectMapper om;
	
	@Override
	public byte[] serialize(Object t) throws SerializationException {
		if(t == null){
			return new byte[0];
		}
		if(isValidObjectType(t)){
			try {
				byte[] ret = om.writeValueAsBytes(t);
				return ret;
			} catch (JsonProcessingException e) {
				throw new SerializationException("Could not convert object "+t.toString()+" to put into session");
			}
		}
		throw new IllegalArgumentException("Object of type "+t.getClass().getName()+" must be converted to JSON String before storing in session");
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		if(bytes == null || bytes.length == 0){
			return null;
		}
		try{
			Object ret = om.readValue(bytes, Object.class);
			if(isValidObjectType(ret)){
				return deserializeObject(bytes,Object.class);
			}
			else{
				throw new IllegalArgumentException("Object type which is unidentified/not allowed found in session.");
			}
		}
		catch(JsonParseException s){
			throw new SerializationException("Could not deserialize "+new String(bytes)+" :: "+s.getMessage());
		}
		catch(JsonMappingException j){
			throw new SerializationException("Could not deserialize "+new String(bytes)+" :: "+j.getMessage());
		}
		catch (IOException i){
			throw new SerializationException("Could not deserialize "+new String(bytes)+" :: "+i.getMessage());
		}
	}
	
	public <T> T deserializeObject(byte[] source, Class<T> type) throws SerializationException {
		try {
			return om.readValue(source, type);
		} catch (Exception ex) {
			throw new SerializationException("Could not read JSON: " + ex.getMessage(), ex);
		}
	}
	
	private boolean isBasicDataType(Object t){
		return (t instanceof String || t instanceof Long || t instanceof Integer 
				|| t instanceof Boolean || t instanceof Character);
	}

	private boolean isHashMap(Object t){
		return t instanceof HashMap;
	}
	
	private boolean isSpringObject(Object t){
		return (t instanceof DefaultSavedRequest || t instanceof SecurityContextImpl
				|| isSpringException(t));
	}
	
	private boolean isSpringException(Object t){
		return t instanceof BadCredentialsException|| t instanceof UsernameNotFoundException
				|| t instanceof CredentialExpiredException || t instanceof DisabledException
				|| t instanceof LockedException	|| t instanceof AuthenticationServiceException
				|| t instanceof AuthenticationServiceException;
	}
	
	private boolean isValidObjectType(Object t){
		return (t==null || isBasicDataType(t) || isSpringObject(t) || isHashMap(t));	
	}

	
}

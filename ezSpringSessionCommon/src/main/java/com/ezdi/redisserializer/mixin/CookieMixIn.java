package com.ezdi.redisserializer.mixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CookieMixIn {
	
	@JsonCreator
	public CookieMixIn(@JsonProperty("name") String name, @JsonProperty("value") String value){}
	
	@JsonProperty("version")
	public abstract int getVersion();
	
	@JsonProperty("comment")
	public abstract String getComment();
	
	@JsonProperty("domain")
	public abstract String getDomain();
	
	@JsonProperty("maxAge")
	public abstract int getMaxAge();
	
	@JsonProperty("path")
	public abstract String getPath();
	
	@JsonProperty("secure")
	public abstract boolean getSecure();
	
	@JsonProperty("httpOnly")
	public abstract boolean isHttpOnly();

}

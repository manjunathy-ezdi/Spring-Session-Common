package com.ezdi.redisserializer.mixin;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class UnmodifiableSetMixin {
	
	@JsonCreator
	public UnmodifiableSetMixin(@JsonProperty("s") Set s) {
		
	}

}

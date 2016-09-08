package com.ezdi.redisserializer.mixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by EZDI\manjunath.y on 14/6/16.
 */
public abstract class AuthenticationServiceExceptionMixIn {

    @JsonCreator
    public AuthenticationServiceExceptionMixIn(@JsonProperty("msg") String msg){}
}

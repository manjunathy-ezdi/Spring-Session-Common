package com.ezdi.redisserializer.mixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by EZDI\manjunath.y on 16/6/16.
 */
public abstract class CredentialsExpiredExceptionMixIn {

    @JsonCreator
    public CredentialsExpiredExceptionMixIn(@JsonProperty("msg") String msg){}
}

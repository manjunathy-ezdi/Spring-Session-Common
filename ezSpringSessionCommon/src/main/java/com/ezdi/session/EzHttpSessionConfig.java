package com.ezdi.session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.ezdi.redisserializer.EzdiRedisSerializer;

@Configuration
@EnableRedisHttpSession
public class EzHttpSessionConfig {
	
	@Value("${redis.hostname}")
	private String redisHostName;

	@Value("${redis.port}")
	private int redisPort;
	
	@Value("${redis.session.timeout}")
	private int sessionTimeOut;

	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory jedisConnectionFectory = new JedisConnectionFactory();
		jedisConnectionFectory.setHostName(redisHostName);
		jedisConnectionFectory.setPort(redisPort);
		return jedisConnectionFectory;
	}
	
	/*@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}*/
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		CookieHttpSessionStrategy cookieHttpSessionStrategy = new CookieHttpSessionStrategy();
		cookieHttpSessionStrategy.setCookieSerializer(cookieSerializer());
		return cookieHttpSessionStrategy;
	}
	
	@Bean
    public CookieSerializer cookieSerializer() {
            DefaultCookieSerializer serializer = new DefaultCookieSerializer();
            serializer.setCookieName("SESSION"); 
            //serializer.setCookiePath("/"); 
            serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$"); 
            return serializer;
    }

	public int getSessionTimeOutInMinutes() {
		return sessionTimeOut;
	}
	
	@Bean(name="springSessionDefaultRedisSerializer")
	public EzdiRedisSerializer springSessionDefaultRedisSerializer(){
		return new EzdiRedisSerializer();
	}
	
	@Bean(name="sessionRedisTemplate")
	public RedisTemplate<Object, Object> sessionRedisTemplate(){
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setDefaultSerializer(springSessionDefaultRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        
        template.setConnectionFactory(connectionFactory());
        
        return template;
	}
	
	@Bean(name="sessionRepository")
	public RedisOperationsSessionRepository sessionRepository(){
		RedisOperationsSessionRepository redisOperationsSessionRepository 
			= new RedisOperationsSessionRepository(sessionRedisTemplate());
		redisOperationsSessionRepository.setDefaultMaxInactiveInterval(getSessionTimeOutInMinutes());
		return redisOperationsSessionRepository;
	}
}
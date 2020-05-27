package com.mlatta.brewclient.model.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Getter;

@Getter
@ConfigurationProperties(prefix="rest.template.config")
public class RestTemplateConfig {
	
	private final Integer maxTotal;
	private final Integer maxPerRoute;
	private final Integer socketTimeout;
	private final Integer connectionRequestTimeout;

	@ConstructorBinding
	public RestTemplateConfig(Integer maxTotal, Integer maxPerRoute, Integer socketTimeout,
			Integer connectionRequestTimeout) {
		this.maxTotal = maxTotal;
		this.maxPerRoute = maxPerRoute;
		this.socketTimeout = socketTimeout;
		this.connectionRequestTimeout = connectionRequestTimeout;
	}
	
}

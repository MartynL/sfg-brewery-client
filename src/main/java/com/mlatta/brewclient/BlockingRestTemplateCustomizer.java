package com.mlatta.brewclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mlatta.brewclient.model.properties.RestTemplateConfig;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

	private final RestTemplateConfig restTemplateConfig;

	@Override
	public void customize(RestTemplate restTemplate) {
		restTemplate.setRequestFactory(this.clientHttpRequestFactory());

	}

	public ClientHttpRequestFactory clientHttpRequestFactory() {
		
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		
		connectionManager.setMaxTotal(restTemplateConfig.getMaxTotal());
		
		connectionManager.setDefaultMaxPerRoute(restTemplateConfig.getMaxPerRoute());
		
		RequestConfig requestConfig = RequestConfig
										.custom()
										.setConnectionRequestTimeout(restTemplateConfig.getConnectionRequestTimeout())
										.setSocketTimeout(restTemplateConfig.getSocketTimeout())
										.build();
		
		CloseableHttpClient httpClient = HttpClients
											.custom()
											.setConnectionManager(connectionManager)
											.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
											.setDefaultRequestConfig(requestConfig)
											.build();
		
		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}

}

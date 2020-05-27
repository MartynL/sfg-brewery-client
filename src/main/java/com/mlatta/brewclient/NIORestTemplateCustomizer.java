package com.mlatta.brewclient;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Only for example. Async client deprecated, possibly due to spring pushing the reactive side of things.
 * @author lattam
 */

//@Component
public class NIORestTemplateCustomizer implements RestTemplateCustomizer {

	@Override
	public void customize(RestTemplate restTemplate) {
		try {
			restTemplate.setRequestFactory(clientHttpRequestFactory());
		} catch (IOReactorException e) {
			e.printStackTrace();
		}
	}

	private ClientHttpRequestFactory clientHttpRequestFactory() throws IOReactorException {
		
		IOReactorConfig ioReactorConfig = IOReactorConfig
									.custom()
									.setConnectTimeout(3000)
									.setIoThreadCount(4)
									.setSoTimeout(3000)
									.build();
		
		final DefaultConnectingIOReactor ioreactor = new DefaultConnectingIOReactor(ioReactorConfig);
		final PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(ioreactor);
		
		connectionManager.setDefaultMaxPerRoute(100);
		connectionManager.setMaxTotal(1000);
		
		CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients
													.custom()
													.setConnectionManager(connectionManager)
													.build();
		
		return new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);
	}

}

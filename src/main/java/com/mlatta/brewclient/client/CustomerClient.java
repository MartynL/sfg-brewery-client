package com.mlatta.brewclient.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mlatta.brewclient.model.CustomerDto;

import lombok.Setter;

@Component
@ConfigurationProperties(prefix="sfg.brewery", ignoreInvalidFields = false)
public class CustomerClient {

	public static final String CUSTOMER_URI_V1 = "/api/v1/customer/";
	
	@Setter	private String apiHost;
	
	private final RestTemplate restTemplate;

	public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public CustomerDto getCustomerById(UUID uuid) {
		return restTemplate.getForObject(apiHost + CUSTOMER_URI_V1 + uuid.toString(), CustomerDto.class);
	}
	
	public URI saveNewCustomer(CustomerDto customerDto) {
		return restTemplate.postForLocation(apiHost + CUSTOMER_URI_V1, customerDto);
	}
	
	public void updateCustomer(UUID uuid, CustomerDto customerDto) {
		restTemplate.put(apiHost + CUSTOMER_URI_V1 + uuid.toString(), customerDto);
	}
	
	public void deleteCustomer(UUID uuid) {
		restTemplate.delete(apiHost + CUSTOMER_URI_V1 + uuid.toString());
	}
	
}

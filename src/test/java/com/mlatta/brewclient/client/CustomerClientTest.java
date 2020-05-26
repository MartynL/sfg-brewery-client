package com.mlatta.brewclient.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mlatta.brewclient.model.CustomerDto;

@SpringBootTest
class CustomerClientTest {

	@Autowired CustomerClient client;
	
	@Test
	void testGetCustomerById() {
		CustomerDto customerDto = client.getCustomerById(UUID.randomUUID());
		assertNotNull(customerDto);
	}

	@Test
	void testSaveNewCustomer() {
		CustomerDto customerDto = CustomerDto.builder().name("Tester Two").build();
		
		URI uri = client.saveNewCustomer(customerDto);
		
		assertNotNull(uri);
		
		System.out.println(uri);
	}

	@Test
	void testUpdateCustomer() {
		CustomerDto customerDto = CustomerDto.builder().name("Tester Three").build();
		client.updateCustomer(UUID.randomUUID(), customerDto);
	}

	@Test
	void testDeleteCustomer() {
		client.deleteCustomer(UUID.randomUUID());
	}

}

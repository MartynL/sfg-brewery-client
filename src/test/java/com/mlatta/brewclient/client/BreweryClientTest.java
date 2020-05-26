package com.mlatta.brewclient.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mlatta.brewclient.model.BeerDto;

@SpringBootTest
class BreweryClientTest {

	@Autowired BreweryClient client;
	
	@Test
	void testGetBeerById() {
		BeerDto beerDto = client.getBeerById(UUID.randomUUID());
		assertNotNull(beerDto);
	}
	
	@Test
	void testSaveNewBeer() {
		BeerDto beerDto = BeerDto.builder().beerName("New Beer").build();
		
		URI uri = client.saveNewBeer(beerDto);
		
		assertNotNull(uri);
		
		System.out.println(uri);
	}

}

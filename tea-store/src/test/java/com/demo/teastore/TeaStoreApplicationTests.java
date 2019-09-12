package com.demo.teastore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.teastore.StoreOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeaStoreApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private ObjectMapper mapper;

	private TestRestTemplate restTemplate = new TestRestTemplate();
	private HttpHeaders headers = new HttpHeaders();

	@Test
	public void getAllOrders() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(baseURL("/store/orders"), HttpMethod.GET,
				entity, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void getOrderByUserId() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(baseURL("/store/orders/user/1"),
				HttpMethod.GET, entity, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void getOrder() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(baseURL("/store/orders/1"), HttpMethod.GET,
				entity, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void createOrder() throws Exception {
		String jsonString = "{\"createdAt\":\"2019-11-31T12:17:58.139+0000\",\"items\":[{\"tea\":{\"name\":\"Test tea\",\"price\":4},\"quantity\":35}],\"user\":{\"firstName\":\"Vivek\",\"lastName\":\"Gupta\",\"email\":\"vivek.gupta@lixar.test\"}}";
		StoreOrder storeOrder = mapper.readValue(jsonString, StoreOrder.class);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<StoreOrder> request = new HttpEntity<>(storeOrder, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(baseURL("/store/orders"), request,
				String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	public void deleteOrder() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(baseURL("/store/orders/1"), HttpMethod.DELETE,
				entity, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}

	private String baseURL(String uri) {
		return "http://localhost:" + port + uri;
	}
}

package com.demo.teastore;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.teastore.entity.StoreOrderEntity;

@RestController
@RequestMapping(produces = "application/json")
public class OrderController {
	private Logger logger = LoggerFactory.getLogger(OrderController.class);
	private StoreService storeService;

	@Autowired
	public OrderController(StoreService storeService) {
		this.storeService = storeService;
	}

	@GetMapping("/orders")
	public List<StoreOrder> getAllOrders() {
		long startTime = System.currentTimeMillis();
		List<StoreOrder> storeOrders = storeService.getAllOrders();
		long endTime = System.currentTimeMillis();
		logger.info("Method - getAllOrders - execution time - " + (endTime - startTime) + "milliseconds.");
		return storeOrders;
	}

	@PostMapping("/orders")
	public ResponseEntity<Long> createOrder(@RequestBody StoreOrder storeOrder) {
		long startTime = System.currentTimeMillis();
		ResponseEntity<Long> responseEntity = null;
		StoreOrderEntity storeOrderEntity = storeService.createOrder(storeOrder);
		if (storeOrderEntity == null) {
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			responseEntity = new ResponseEntity<>(storeOrderEntity.getId(), HttpStatus.CREATED);
		}
		long endTime = System.currentTimeMillis();
		logger.info("Method - createOrder - execution time - " + (endTime - startTime) + "milliseconds.");
		return responseEntity;
	}

	@DeleteMapping("orders/{id}")
	public ResponseEntity<Long> deleteOrder(@PathVariable("id") Long id) {
		long startTime = System.currentTimeMillis();
		ResponseEntity<Long> responseEntity = null;
		StoreOrderEntity storeOrderEntity = storeService.deleteOrder(id);
		if (storeOrderEntity == null) {
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			responseEntity = new ResponseEntity<>(id, HttpStatus.OK);
		}
		long endTime = System.currentTimeMillis();
		logger.info("Method - deleteOrder - execution time - " + (endTime - startTime) + "milliseconds.");
		return responseEntity;
	}

	@GetMapping("/orders/user/{id}")
	public List<StoreOrder> getOrderByUserId(@PathVariable("id") Long id) {
		long startTime = System.currentTimeMillis();
		List<StoreOrder> storeOrders = storeService.getOrderByUserId(id);
		long endTime = System.currentTimeMillis();
		logger.info("Method - getOrderByUserId - execution time - " + (endTime - startTime) + "milliseconds.");
		return storeOrders;
	}
	
	@GetMapping("/orders/{id}")
	public StoreOrder getOrder(@PathVariable("id") Long id) {
		long startTime = System.currentTimeMillis();
		StoreOrder storeOrder = storeService.getOrder(id);
		long endTime = System.currentTimeMillis();
		logger.info("Method - getOrder - execution time - " + (endTime - startTime) + "milliseconds.");
		return storeOrder;
	}
}

package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.dto.OrderDto;
import com.order.dto.OrderRequest;
import com.order.service.OrderService;

@RestController
@RequestMapping(name = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping(value = "/placeOrder")
	public @ResponseBody OrderDto createOrder(@RequestBody OrderRequest orderRequest)
	{
		return orderService.placeOrder(orderRequest.getProductCode(), orderRequest.getQuantity());
	}
	
	@GetMapping(value = "/getAllOrders")
	public @ResponseBody List<OrderDto> getAllOrders()
	{
		return orderService.getAllOrders();
	}
	
	@GetMapping(value = "/getOrder")
	public @ResponseBody OrderDto getOrderByCode(@RequestParam String orderCode)
	{
		return orderService.getOrderByCode(orderCode);
	}
}

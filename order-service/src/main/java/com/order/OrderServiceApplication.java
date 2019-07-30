package com.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.order.service.OrderService;

@SpringBootApplication
public class OrderServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
		OrderService orderService = new OrderService();
		orderService.createOrder();
	}
	
	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}

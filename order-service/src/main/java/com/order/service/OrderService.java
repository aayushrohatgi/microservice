package com.order.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.order.dao.OrderDao;
import com.order.dto.EventData;
import com.order.dto.OrderDto;

@Service
public class OrderService {
	
	 @Autowired
	 private RestTemplate restTemplate;
	 
	 @Autowired
	 private EurekaClient eurekaClient;
	 
	 @Autowired
	 private OrderDao orderDao;

	 @Autowired
	 private EventLoggingHandler eventLoggingHandler;
	 
	public boolean createOrder() 
	{
		Application application = eurekaClient.getApplication("inventory-service");
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "inventory/get/?productId=1&quantity=1";
        System.out.println("URL" + url);
        Map<String, String> params = new HashMap<>();
        Boolean b = restTemplate.getForObject(url, Boolean.class, params);
        System.out.println(b);
        OrderDto order = new OrderDto();
        order.setProductCode("1");
        order.setQuantity(1);
        orderDao.save(order);
        eventLoggingHandler.sendOrderCreatedEvent(generateEvent(order));
        return b;
	}
	
	private EventData generateEvent(OrderDto dto) {
		EventData data = new EventData();
		data.setEventName("Order " + dto.getOrderCode() + " has been created.");
		data.setTimeStamp(LocalDateTime.now().toString());
		return data;
	}
}

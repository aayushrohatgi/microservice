package com.order.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.order.dao.OrderDao;
import com.order.dto.EventData;
import com.order.dto.NotificationData;
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
	 
	@Autowired
	private NotificationHandler notificationHandler;
	 
	public OrderDto placeOrder(String productCode, int quantity) {
		OrderDto order = null;
		Boolean stockExists = checkIfStockExists(productCode, quantity);
		if(stockExists)
		{
			order = new OrderDto();
			order.setProductCode(productCode);
			order.setQuantity(quantity);
			orderDao.save(order);
			eventLoggingHandler.sendOrderCreatedEvent(generateEvent(order));
			notificationHandler.sendOrderNotification(generateNotificationData(order.getOrderCode()));
		}
	    return order;
	}
	
	private Boolean checkIfStockExists(String productCode, int quantity) {
		Application application = eurekaClient.getApplication("inventory-service");
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "inventory/get/?productId=" +productCode+"&quantity="+quantity;
        System.out.println("URL" + url);
        Map<String, String> params = new HashMap<>();
        Boolean stockExists = restTemplate.getForObject(url, Boolean.class, params);
		return stockExists;
	}
	
	private EventData generateEvent(OrderDto dto) {
		EventData data = new EventData();
		data.setEventName("Order " + dto.getOrderCode() + " has been created.");
		data.setTimeStamp(LocalDateTime.now().toString());
		return data;
	}
	
	private NotificationData generateNotificationData(int orderCode) {
		NotificationData notificationData = new NotificationData();
		notificationData.setContent("Order "+ orderCode + " has been created and will be shipped shortly.");
		notificationData.setEmail("abc@example.com");
		notificationData.setMobileNo("9999999999");
		return notificationData;
	}

	

	public List<OrderDto> getAllOrders() {
		return orderDao.findAll();
	}

	public OrderDto getOrderByCode(String orderCode) {
		OrderDto orderDto = null;
		Optional<OrderDto> orderOptional = orderDao.findById(orderCode);
		if(orderOptional.isPresent())
		{
			orderDto = orderOptional.get();
		}
		return orderDto;
	}
	
}

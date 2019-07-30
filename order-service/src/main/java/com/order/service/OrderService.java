package com.order.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@Service
public class OrderService {
	
	 @Autowired
	    private RestTemplate restTemplate;
	    @Autowired
	    private EurekaClient eurekaClient;

	
	public boolean createOrder() 
	{
		Application application = eurekaClient.getApplication("inventory-service");
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "inventory/get/?productId=1&quantity=1";
        System.out.println("URL" + url);
        Map<String, String> params = new HashMap<>();
        Boolean b = restTemplate.getForObject(url, Boolean.class, params);
        System.out.println(b);
        return b;
	}
}

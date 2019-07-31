package com.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.channel.NotificationOutSourcer;
import com.order.dto.NotificationData;

@Component
@EnableBinding(NotificationOutSourcer.class)
public class NotificationHandler {

	@Autowired
	private NotificationOutSourcer notificationOutSourcer;
	
	public void sendOrderNotification(NotificationData notification)
	{
		ObjectMapper mapper = new ObjectMapper();
		try {
			notificationOutSourcer.sendEvent().send(MessageBuilder.withPayload(mapper.writeValueAsString(notification)).build());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}

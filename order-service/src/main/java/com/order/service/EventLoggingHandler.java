package com.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.channel.EventLogOutSourcer;
import com.order.dto.EventData;

@Component
@EnableBinding(EventLogOutSourcer.class)
public class EventLoggingHandler {

	@Autowired
	private EventLogOutSourcer eventLogOutSourcer;
	
	public void sendOrderCreatedEvent(EventData event)
	{
		ObjectMapper mapper = new ObjectMapper();
		try {
			eventLogOutSourcer.sendEvent().send(MessageBuilder.withPayload(mapper.writeValueAsString(event)).build());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}

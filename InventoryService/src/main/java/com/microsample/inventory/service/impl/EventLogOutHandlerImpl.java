package com.microsample.inventory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsample.inventory.channel.EventLogOutSourcer;
import com.microsample.inventory.dto.EventData;
import com.microsample.inventory.service.EventLogOutHandler;

@Component
@EnableBinding(EventLogOutSourcer.class)
public class EventLogOutHandlerImpl implements EventLogOutHandler {

	@Autowired
	private EventLogOutSourcer eventLogOutHandler;

	@Override
	public void sendInventoryChangeEvent(EventData event) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			getEventLogOutHandler().sendEvent().send(MessageBuilder.withPayload(mapper.writeValueAsString(event)).build());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public EventLogOutSourcer getEventLogOutHandler() {
		return eventLogOutHandler;
	}

	public void setEventLogOutHandler(EventLogOutSourcer eventLogOutHandler) {
		this.eventLogOutHandler = eventLogOutHandler;
	}
}

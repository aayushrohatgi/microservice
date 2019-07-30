package com.microsample.inventory.service.impl;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.microsample.inventory.dto.EventData;
import com.microsample.inventory.channel.EventLogOutSourcer;
import com.microsample.inventory.service.EventLogOutHandler;

@Component
@EnableBinding(EventLogOutSourcer.class)
public class EventLogOutHandlerImpl implements EventLogOutHandler {
	
	@Override
	@SendTo(value = EventLogOutSourcer.OUTPUT)
	public void sendInventoryChangeEvent(EventData event) {
		// nothing to do
	}
}

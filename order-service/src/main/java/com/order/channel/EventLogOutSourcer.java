package com.order.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventLogOutSourcer {

	public static final String OUTPUT = "eventLogOutSource";
	
	@Output(OUTPUT)
	MessageChannel sendEvent();
}

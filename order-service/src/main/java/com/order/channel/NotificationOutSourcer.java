package com.order.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface NotificationOutSourcer {

	public static final String OUTPUT = "notificationOutSource";
	
	@Output(OUTPUT)
	MessageChannel sendEvent();


}

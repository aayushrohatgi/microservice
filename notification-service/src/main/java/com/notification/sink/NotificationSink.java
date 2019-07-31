package com.notification.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface NotificationSink {
	
	String INPUT = "notificationSink";

	@Input(INPUT)
	SubscribableChannel event();


}

package com.microsample.eventLogger.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EventReciever {
	
	String INPUT = "eventSink";

	@Input(INPUT)
	SubscribableChannel event();
}

package com.microsample.eventLogger.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.microsample.eventLogger.dto.EventData;
import com.microsample.eventLogger.service.EventLoggerService;
import com.microsample.eventLogger.service.EventRecieverListener;
import com.microsample.eventLogger.sink.EventReciever;

@Component
@EnableBinding(EventReciever.class)
public class EventRecieverListenerImpl implements EventRecieverListener {

	@Autowired
	private EventLoggerService eventLoggerService;
	
	@StreamListener(target = EventReciever.INPUT)
	public void fetchAndRegisterEvent(EventData data) {
		LocalDateTime timestamp = LocalDateTime.parse(data.getTimeStamp(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		getEventLoggerService().logEvent(data.getEventName(), timestamp);
	}

	public EventLoggerService getEventLoggerService() {
		return eventLoggerService;
	}

	public void setEventLoggerService(EventLoggerService eventLoggerService) {
		this.eventLoggerService = eventLoggerService;
	}
}

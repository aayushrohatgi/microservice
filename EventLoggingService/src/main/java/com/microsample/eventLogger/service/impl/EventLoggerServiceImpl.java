package com.microsample.eventLogger.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsample.eventLogger.dao.EventLoggerDao;
import com.microsample.eventLogger.dto.EventDto;
import com.microsample.eventLogger.service.EventLoggerService;

@Service
public class EventLoggerServiceImpl implements EventLoggerService {

	@Autowired
	private EventLoggerDao eventLoggerDao;

	public EventLoggerDao getEventLoggerDao() {
		return eventLoggerDao;
	}

	public void setEventLoggerDao(EventLoggerDao eventLoggerDao) {
		this.eventLoggerDao = eventLoggerDao;
	}

	@Override
	public void logEvent(String name, LocalDateTime timestamp) {
		EventDto event = new EventDto();
		event.setEventName(name);
		event.setEventTimeStamp(timestamp);
		getEventLoggerDao().save(event);
	}
}

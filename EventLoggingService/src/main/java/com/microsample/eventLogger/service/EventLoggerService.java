package com.microsample.eventLogger.service;

import java.time.LocalDateTime;

public interface EventLoggerService {
	
	public void logEvent(String name, LocalDateTime timestamp);
}

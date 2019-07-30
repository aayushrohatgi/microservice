package com.microsample.eventLogger.service;

import com.microsample.eventLogger.dto.EventData;

public interface EventRecieverListener {

	void fetchAndRegisterEvent(EventData data);
}

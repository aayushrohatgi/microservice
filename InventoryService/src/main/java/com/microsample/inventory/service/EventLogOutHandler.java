package com.microsample.inventory.service;

import com.microsample.inventory.dto.EventData;

public interface EventLogOutHandler {

	void sendInventoryChangeEvent(EventData event);
}

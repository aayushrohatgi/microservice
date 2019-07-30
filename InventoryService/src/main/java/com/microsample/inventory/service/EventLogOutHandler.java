package com.microsample.inventory.service;

import com.microsample.inventory.dto.EventData;

public interface EventLogOutHandler {

	public void sendInventoryChangeEvent(EventData event);
}

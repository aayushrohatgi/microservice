package com.microsample.inventory.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsample.inventory.dto.EventData;
import com.microsample.inventory.dto.ProductDto;
import com.microsample.inventory.repository.InventoryDao;
import com.microsample.inventory.service.EventLogOutHandler;
import com.microsample.inventory.service.InventoryStockManagementService;

@Service
public class InventoryStockManagementServiceImpl implements InventoryStockManagementService {

	@Autowired
	private InventoryDao inventoryDao;

	@Autowired
	private EventLogOutHandler eventLogHandler;

	@Override
	public boolean isProductInStock(final String productCode, final int requiredQuantity) {
		boolean isInStock = false;
		Optional<ProductDto> product = getInventoryDao().findById(productCode);
		if (product.isPresent()) {
			ProductDto dto = product.get();
			int stockAvailable = dto.getStockAvailable();
			if (stockAvailable >= requiredQuantity) {
				isInStock = true;
				dto.setStockAvailable(stockAvailable - requiredQuantity);
				getInventoryDao().save(dto);
				getEventLogHandler().sendInventoryChangeEvent(generateEvent(dto, requiredQuantity));
			}
		}
		return isInStock;
	}

	private EventData generateEvent(ProductDto dto, int requiredQuantity) {
		EventData data = new EventData();
		data.setEventName("Inventory Reduced for product " + dto.getCode() + " by " + requiredQuantity);
		data.setTimeStamp(LocalDateTime.now().toString());
		return data;
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public EventLogOutHandler getEventLogHandler() {
		return eventLogHandler;
	}

	public void setEventLogHandler(EventLogOutHandler eventLogHandler) {
		this.eventLogHandler = eventLogHandler;
	}

}

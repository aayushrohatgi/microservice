package com.microsample.inventory.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsample.inventory.dto.ProductDto;
import com.microsample.inventory.repository.InventoryDao;
import com.microsample.inventory.service.InventoryStockManagementService;

@Service
public class InventoryStockManagementServiceImpl implements InventoryStockManagementService{

	@Autowired
	private InventoryDao inventoryDao;
	
	@Override
	public boolean isProductInStock(final String productCode, final int requiredQuantity) {
		boolean isInStock = false;
		Optional<ProductDto> product = getInventoryDao().findById(productCode);
		if (product.isPresent()) {
			ProductDto dto = product.get();
			int stockAvailable = dto.getStockAvailable();
			if (stockAvailable >= requiredQuantity) {
				isInStock = true;
			}
			dto.setStockAvailable(stockAvailable - requiredQuantity);
			getInventoryDao().save(dto);
		}
		return isInStock;
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

}

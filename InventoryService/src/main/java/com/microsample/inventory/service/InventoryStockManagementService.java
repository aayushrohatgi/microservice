package com.microsample.inventory.service;

public interface InventoryStockManagementService {

	public boolean isProductInStock(final String productCode, final int requiredQuantity);
}

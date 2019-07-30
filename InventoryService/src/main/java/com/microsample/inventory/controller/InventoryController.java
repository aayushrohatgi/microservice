package com.microsample.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microsample.inventory.service.InventoryStockManagementService;

@RestController
public class InventoryController {

	@Autowired
	private InventoryStockManagementService inventoryStockManagementService;

	@ResponseBody
	@GetMapping("/inventory/get")
	public Boolean checkStockAnddeduct(@RequestParam final String productId, @RequestParam final int quantity) {
		 return getInventoryStockManagementService().isProductInStock(productId,
		 quantity);
	}

	public InventoryStockManagementService getInventoryStockManagementService() {
		return inventoryStockManagementService;
	}

	public void setInventoryStockManagementService(InventoryStockManagementService inventoryStockManagementService) {
		this.inventoryStockManagementService = inventoryStockManagementService;
	}
}

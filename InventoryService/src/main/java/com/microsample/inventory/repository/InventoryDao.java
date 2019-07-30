package com.microsample.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microsample.inventory.dto.ProductDto;

@Repository
public interface InventoryDao extends JpaRepository<ProductDto, String> {
	
}

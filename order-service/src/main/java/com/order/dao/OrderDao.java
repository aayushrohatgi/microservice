package com.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.dto.OrderDto;

public interface OrderDao extends JpaRepository<OrderDto, String> {
	
	
}

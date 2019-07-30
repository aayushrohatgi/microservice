package com.microsample.eventLogger.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microsample.eventLogger.dto.EventDto;

@Repository
public interface EventLoggerDao extends JpaRepository<EventDto, Integer> {

}

package com.microsample.eventLogger.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eventlogs")
public class EventDto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String eventName;

	@Column(name = "timestamp")
	private LocalDateTime eventTimeStamp;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public LocalDateTime getEventTimeStamp() {
		return eventTimeStamp;
	}

	public void setEventTimeStamp(LocalDateTime eventTimeStamp) {
		this.eventTimeStamp = eventTimeStamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

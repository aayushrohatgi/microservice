package com.notification.service;

import org.springframework.stereotype.Service;

import com.notification.dto.NotificationData;

@Service
public class NotificationService {

	public void sendEmailNotification(NotificationData notificationDto) {
		System.out.println("Email notification Sent to"+ notificationDto.getEmail());
	}
	
	public void sendSMSNotification(NotificationData notificationDto) {
		System.out.println("SMS notification Sent to"+ notificationDto.getMobileNo());
	}
	
}

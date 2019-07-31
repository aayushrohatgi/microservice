package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.notification.dto.NotificationData;
import com.notification.sink.NotificationSink;

@Component
@EnableBinding(NotificationSink.class)
public class NotificationListener {

	@Autowired
	private NotificationService notificationService;
	
	@StreamListener(target = NotificationSink.INPUT)
	public void registerNotificationEvent(NotificationData notificationDto)
	{
		notificationService.sendEmailNotification(notificationDto);
		notificationService.sendSMSNotification(notificationDto);
	}
}

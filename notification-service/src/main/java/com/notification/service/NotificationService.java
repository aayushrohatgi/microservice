package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.notification.dto.NotificationData;

@Service
public class NotificationService {
	
	@Autowired
    private JavaMailSender javaMailSender;

	public void sendEmailNotification(NotificationData notificationDto) {
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(notificationDto.getEmail());
        msg.setFrom(notificationDto.getEmail());
        msg.setSubject("Testing from Spring Boot");
        msg.setText(notificationDto.getContent());

        javaMailSender.send(msg);
		System.out.println("Email notification Sent to"+ notificationDto.getEmail());
	}
	
	public void sendSMSNotification(NotificationData notificationDto) {
		System.out.println("SMS notification Sent to"+ notificationDto.getMobileNo());
	}
	
}

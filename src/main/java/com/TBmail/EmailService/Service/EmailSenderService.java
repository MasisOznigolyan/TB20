package com.TBmail.EmailService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String toEmail, String subject, String body ) {
		SimpleMailMessage message= new SimpleMailMessage();
		message.setFrom("oznigolyan3@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		try {
			mailSender.send(message);
			System.out.println("Sent successfully");
			return true;
		}catch(MailException e) {
			System.out.println("error occured while sending mail");
			return false;
		}
		
		
		
	}
	
}

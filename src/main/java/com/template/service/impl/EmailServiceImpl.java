package com.template.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.template.model.Doctor;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private SpringTemplateEngine springTemplateEngine;
	
	@Autowired
    ResourceLoader resourceLoader;

	@Override
	@Retryable(retryFor = {MessagingException.class} ,backoff = @Backoff(delay = 1000,multiplier = 5), maxAttempts = 3)
	public String sendDoctorRegistrationEmail(Doctor doctor, String password) throws MessagingException {
			
				System.err.println("email==>"+doctor.getEmail() +" Time :"+new Date().getSeconds());
				
				final MimeMessage message = javaMailSender.createMimeMessage();
				final MimeMessageHelper helper = new MimeMessageHelper(message,
						MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
				//Resource resource = resourceLoader.getResource("classpath:SilvetTouchLogo.png");
				
				
				//System.err.println(resource.getContentAsByteArray());
				final Context context = new Context();
				context.setVariable("email", doctor.getEmail());
				context.setVariable("password", password);
				context.setVariable("name", doctor.getName());
				//context.setVariable("logo",resource.getContentAsByteArray());
				
				final String html = springTemplateEngine.process("doctor-registration.html", context);
				
				helper.setTo(doctor.getEmail());
				helper.setText(html, true);
				helper.setSubject("Congratulations "+doctor.getName());
				helper.setFrom("129.ap9@gmail.com");
				
				javaMailSender.send(message);
				
				throw new MessagingException();

		
	}
	

	
	@Recover
	public String recover(Exception ex) {
	   System.out.println("--> service failed");

	   return "service unavailable";
	}



}
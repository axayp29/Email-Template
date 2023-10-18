package com.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.model.Doctor;
import com.template.repo.DoctorRepo;
import com.template.service.impl.EmailService;

import jakarta.mail.MessagingException;

@RestController
public class DemoController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private DoctorRepo repo;
	
	@RequestMapping(value = "/demo")
	public String demo() throws MessagingException
	{
		
		Doctor doctor =repo.findById(8L).orElse(null);
		
		String a  = emailService.sendDoctorRegistrationEmail(doctor, "40,000");
		
		
		return a;
	}

}
package com.template.service.impl;

import com.template.model.Doctor;

import jakarta.mail.MessagingException;

public interface EmailService {

	public String sendDoctorRegistrationEmail(Doctor doctor,String password) throws MessagingException;
	
	//public void sendDoctorResetPasswordEmail(Doctor doctor,String password);
	
	//public void sendForgetPasswordEmail(Doctor doctor,String link);
	
	//public void sendVerificationEmail(Patient patient,String link);
	
	//public void sendPaymentReceiptEmail(Appointment appointment,String filePath);
	
	//public void sendPaymentRefundReceiptEmail(Appointment appointment,String filePath);
}
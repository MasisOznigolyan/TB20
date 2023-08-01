package com.TBmail.EmailService.Test.Generator;

import com.TBmail.EmailService.Collections.Email;
import com.TBmail.EmailService.Response.EmailResponse;
import com.github.javafaker.Faker;

public class EmailGenerator {

	private Faker faker = new Faker();
	
	public EmailResponse generateEmailResponse() {
		String id= faker.idNumber().valid();
		String emailId=faker.idNumber().valid();
		String email=faker.internet().emailAddress();
		return new EmailResponse(id,emailId,email);
	}
	
	public Email generateEmail() {
		String id= faker.idNumber().valid();
		String emailId=faker.idNumber().valid();
		String email=faker.internet().emailAddress();
		return new Email(id,emailId,email);
	}
}

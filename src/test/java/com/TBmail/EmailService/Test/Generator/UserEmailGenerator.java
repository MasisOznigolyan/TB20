package com.TBmail.EmailService.Test.Generator;

import com.TBmail.EmailService.Collections.Email;
import com.TBmail.EmailService.Collections.User;
import com.TBmail.EmailService.Collections.UserEmail;
import com.TBmail.EmailService.Response.UserEmailResponse;
import com.github.javafaker.Faker;

public class UserEmailGenerator {
	Faker faker= new Faker();
	
	public UserEmailResponse generateUserEmailResponse() {
		String id=faker.idNumber().valid();
		String userEmailId=faker.idNumber().valid();
		
		UserGenerator userGenerator= new UserGenerator();
		EmailGenerator emailGenerator= new EmailGenerator();
		
		User userId=userGenerator.generateUser();
		Email emailId=emailGenerator.generateEmail();
		
		return new UserEmailResponse(id,userEmailId,userId,emailId);
		 
	}
	
	public UserEmail generateUserEmail() {
		String id=faker.idNumber().valid();
		String userEmailId=faker.idNumber().valid();
		
		UserGenerator userGenerator= new UserGenerator();
		EmailGenerator emailGenerator= new EmailGenerator();
		
		User userId=userGenerator.generateUser();
		Email emailId=emailGenerator.generateEmail();
		
		return new UserEmail(id,userEmailId,userId,emailId);
		 
	}
	
}

package com.TBmail.EmailService.Test.Generator;

import java.util.Locale;

import com.TBmail.EmailService.Response.UserResponse;
import com.github.javafaker.Faker;

public class UserGenerator {

	private Faker faker = new Faker(new Locale("tr"));
	
	public  UserResponse generateUser() {
		String id= faker.idNumber().valid();
		String userId=faker.idNumber().valid();
		String name=faker.name().fullName();
		
		return new UserResponse(id,userId,name);
	}
}

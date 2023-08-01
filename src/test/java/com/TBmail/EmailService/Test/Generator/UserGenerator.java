package com.TBmail.EmailService.Test.Generator;

import com.TBmail.EmailService.Collections.User;
import com.TBmail.EmailService.Response.UserResponse;
import com.github.javafaker.Faker;

public class UserGenerator {

	private Faker faker = new Faker();
	
	public  UserResponse generateUserResponse() {
		String id= faker.idNumber().valid();
		String userId=faker.idNumber().valid();
		String name=faker.name().fullName();
		
		return new UserResponse(id,userId,name);
	}
	
	public  User generateUser() {
		String id= faker.idNumber().valid();
		String userId=faker.idNumber().valid();
		String name=faker.name().fullName();
		
		return new User(id,userId,name);
	}
	
}

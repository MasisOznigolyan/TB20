package com.TBmail.EmailService.Test.Generator;

import com.TBmail.EmailService.Collections.LastSent;
import com.TBmail.EmailService.Collections.News;
import com.TBmail.EmailService.Collections.UserEmail;
import com.TBmail.EmailService.Response.LastSentResponse;
import com.TBmail.EmailService.Response.NewsResponse;
import com.github.javafaker.Faker;

public class LastSentGenerator {
	Faker faker = new Faker();
	
	
	public LastSent generateLastSent() {
		String id=faker.idNumber().valid();
		String LastSentId=faker.idNumber().valid();
		
		UserEmailGenerator userEmailGenerator= new UserEmailGenerator();
		NewsGenerator newsGenerator= new NewsGenerator();
		
		UserEmail userEmailId=userEmailGenerator.generateUserEmail();
		News newsId=newsGenerator.generateNews();
		
		return new LastSent(id,LastSentId,userEmailId,newsId);
	}
	
	public LastSentResponse generateLastSentResponse() {
		String id=faker.idNumber().valid();
		String LastSentId=faker.idNumber().valid();
		
		UserEmailGenerator userEmailGenerator= new UserEmailGenerator();
		NewsGenerator newsGenerator= new NewsGenerator();
		
		UserEmail userEmailId=userEmailGenerator.generateUserEmail();
		NewsResponse newsId=newsGenerator.generateNewsResponse();
		
		return new LastSentResponse(id,LastSentId,userEmailId,newsId);
	}
}

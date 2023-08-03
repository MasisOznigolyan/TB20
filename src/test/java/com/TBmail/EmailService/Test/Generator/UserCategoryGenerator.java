package com.TBmail.EmailService.Test.Generator;

import com.TBmail.EmailService.Collections.NewsCategory;
import com.TBmail.EmailService.Collections.User;
import com.TBmail.EmailService.Collections.UserCategory;
import com.TBmail.EmailService.Response.UserCategoryResponse;
import com.github.javafaker.Faker;

public class UserCategoryGenerator {
	Faker faker= new Faker();
	
	public UserCategoryResponse generateUserCategoryResponse() {
		 String id= faker.idNumber().valid();


		String userCategoryId=faker.idNumber().valid();
		
		UserGenerator userGenerator= new UserGenerator();
		User userId= userGenerator.generateUser();
		
		NewsCategoryGenerator newsCategoryGenerator= new NewsCategoryGenerator();
		NewsCategory newsCategoryId= newsCategoryGenerator.generateNewsCategory();
		
		return new UserCategoryResponse(id,userCategoryId,userId,newsCategoryId);
	}
	
	public UserCategory generateUserCategory() {
		 String id= faker.idNumber().valid();


		String userCategoryId=faker.idNumber().valid();
		
		UserGenerator userGenerator= new UserGenerator();
		User userId= userGenerator.generateUser();
		
		NewsCategoryGenerator newsCategoryGenerator= new NewsCategoryGenerator();
		NewsCategory newsCategoryId= newsCategoryGenerator.generateNewsCategory();
		
		return new UserCategory(id,userCategoryId,userId,newsCategoryId);
	}
}

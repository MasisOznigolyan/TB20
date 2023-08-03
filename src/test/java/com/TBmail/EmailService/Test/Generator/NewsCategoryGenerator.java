package com.TBmail.EmailService.Test.Generator;

import com.TBmail.EmailService.Collections.NewsCategory;
import com.TBmail.EmailService.Response.NewsCategoryResponse;
import com.github.javafaker.Faker;

public class NewsCategoryGenerator {
	public NewsCategoryResponse generateNewsCategoryResponse() {
		
		Faker faker= new Faker();
		
		String id=faker.idNumber().valid();
		
	    String newsCategoryId=faker.idNumber().valid();
		
	    String name=faker.team().sport();
		
	    String categoryUrl=faker.internet().url();
	    
	    return new NewsCategoryResponse(id,newsCategoryId,name,categoryUrl);
	}
	
public NewsCategory generateNewsCategory() {
		
		Faker faker= new Faker();
		
		String id=faker.idNumber().valid();
		
	    String newsCategoryId=faker.idNumber().valid();
		
	    String name=faker.team().sport();
		
	    String categoryUrl=faker.internet().url();
	    
	    return new NewsCategory(id,newsCategoryId,name,categoryUrl);
	}
}

package com.TBmail.EmailService.Test.Generator;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

import com.TBmail.EmailService.Collections.News;
import com.TBmail.EmailService.Collections.NewsCategory;
import com.TBmail.EmailService.Response.NewsResponse;
import com.github.javafaker.Faker;

public class NewsGenerator {

	NewsCategoryGenerator ncg= new  NewsCategoryGenerator();
	Faker faker= new Faker();
	
	public NewsResponse generateNewsResponse() {
		
		String id=faker.idNumber().valid();
		
		
	   	String newsId=faker.idNumber().valid();
		
		
		String url=faker.internet().url();
		
		
	   	String title=faker.esports().team();
		
		
	   	String content=faker.esports().team();
		
	   	LocalDateTime postDateTime=LocalDateTime.of(2023, 1, 1, 0, 0);
		
	   	NewsCategory categoryId=ncg.generateNewsCategory();
	   	
	   	return new NewsResponse(id,newsId,url,title,content,postDateTime,categoryId );
	}
	
	
public News generateNews() {
		
		String id=faker.idNumber().valid();
		
		
	   	String newsId=faker.idNumber().valid();
		
		
		String url=faker.internet().url();
		
		
	   	String title=faker.esports().team();
		
		
	   	String content=faker.esports().team();
		
	   	LocalDateTime postDateTime=LocalDateTime.of(2023, 1, 1, 0, 0);
		
	   	NewsCategory categoryId=ncg.generateNewsCategory();
	   	
	   	return new News(id,newsId,url,title,content,postDateTime,categoryId );
	}
	
	
	
	
	
	
	
	
	public static LocalDateTime generateRandomDateTime(LocalDateTime startInclusive, LocalDateTime endInclusive) {
        long startEpoch = startInclusive.toEpochSecond(ZoneOffset.UTC);
        long endEpoch = endInclusive.toEpochSecond(ZoneOffset.UTC);

        long randomEpoch = ThreadLocalRandom.current().nextLong(startEpoch, endEpoch + 1);
        return LocalDateTime.ofEpochSecond(randomEpoch, 0, ZoneOffset.UTC);
    }
}

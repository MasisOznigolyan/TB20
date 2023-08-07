package com.TBmail.EmailService.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TBmail.EmailService.Init;
import com.TBmail.EmailService.Collections.Email;
import com.TBmail.EmailService.Collections.LastSent;
import com.TBmail.EmailService.Collections.News;
import com.TBmail.EmailService.Collections.NewsCategory;
import com.TBmail.EmailService.Collections.User;
import com.TBmail.EmailService.Collections.UserCategory;
import com.TBmail.EmailService.Collections.UserEmail;
import com.TBmail.EmailService.Parser.GetHead;
import com.TBmail.EmailService.Parser.LastNews;
import com.TBmail.EmailService.Parser.MailContent;

@RestController
@RequestMapping("/Admin")
public class Admin {
	
	@Autowired
	private Init init;

	private static final Logger logInfo=LoggerFactory.getLogger(Admin.class);
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private EmailController emailController;
	
	@Autowired
	private UserEmailController userEmailController;
	
	@Autowired
	private NewsCategoryController newsCategoryController;
	
	@Autowired
	private UserCategoryController userCategoryController;
	
	@Autowired
	private NewsController newsController;
	
	@Autowired
	private LastSentController lastSentController;
	
	@PostMapping("/add")
	public ResponseEntity<Void> addPeople(@RequestParam String name,
										  @RequestParam String emailAddress,
										  @RequestParam String CategoryName,
										  @RequestParam String CategoryUrl,
										  @RequestParam(value = "LastNewsUrl", required = false) String LastNewsUrl) throws Exception{
		
		User user = new User();
		user.setName(name);
		userController.createUser(user);
		
		Email email = new Email();
		email.setEMail(emailAddress);
		emailController.createEmail(email);
		
		UserEmail userEmail =new UserEmail();
		
		userEmail.setEmailId(email);
		userEmail.setUserId(user);
		
		userEmailController.addUserEmail(userEmail);
		
		NewsCategory newsCategory = new NewsCategory();
		newsCategory.setCategoryUrl(CategoryUrl);
		newsCategory.setName(CategoryName);
		newsCategoryController.addNewsCategory(newsCategory);
		
		UserCategory userCategory= new UserCategory();
		userCategory.setNewsCategoryId(newsCategory);
		userCategory.setUserId(user);
		userCategoryController.createUserCategory(userCategory);
		
		News news= new News();
		news.setCategoryId(newsCategory);
		
		if(LastNewsUrl==null) {
			String url=new String();
			String page=MailContent.getHtml(CategoryUrl);
			url= LastNews.getNewsUrl(page);
			news.setUrl(url);
			
		}
		else {
			news.setUrl(LastNewsUrl);
		}
		
		String title=GetHead.getHeadJsoup(news.getUrl());
		news.setTitle(title);
		String content=MailContent.getContent(news.getUrl());
		String postDate=LastNews.getLastNewsTime(news.getUrl());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(postDate, formatter);
        
        ZoneId gmtPlus3 = ZoneId.of("GMT+3");
        ZonedDateTime zonedDateTime = localDateTime.atZone(gmtPlus3);
        LocalDateTime dateTime = zonedDateTime.toLocalDateTime();
        
		news.setContent(content);
		news.setPostDate(dateTime);
		newsController.addNews(news);
		
		
        LastSent lastSent= new LastSent();
        lastSent.setNewsId(news);
        lastSent.setUserEmailId(userEmail);
		lastSentController.addLastSent(lastSent);
		
		logInfo.info("New User Added to DB");
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@DeleteMapping("/resetDb")
	public ResponseEntity<Void> resetDb(){
		init.initDb();
		logInfo.info("DB is cleared and reset");
		return ResponseEntity.status(HttpStatus.GONE).build();
		
	}
	
}

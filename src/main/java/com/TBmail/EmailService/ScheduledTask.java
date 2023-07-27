package com.TBmail.EmailService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.TBmail.EmailService.Collections.LastSent;
import com.TBmail.EmailService.Collections.News;
import com.TBmail.EmailService.Collections.UserEmail;
import com.TBmail.EmailService.Parser.GetHead;
import com.TBmail.EmailService.Parser.LastNews;
import com.TBmail.EmailService.Parser.MailContent;
import com.TBmail.EmailService.Response.EmailResponse;
import com.TBmail.EmailService.Response.LastSentResponse;
import com.TBmail.EmailService.Response.NewsResponse;
import com.TBmail.EmailService.Response.UserCategoryResponse;
import com.TBmail.EmailService.Response.UserEmailResponse;
import com.TBmail.EmailService.Response.UserResponse;
import com.TBmail.EmailService.Service.EmailSenderService;
import com.TBmail.EmailService.Service.EmailService;
import com.TBmail.EmailService.Service.LastSentService;
import com.TBmail.EmailService.Service.NewsService;
import com.TBmail.EmailService.Service.UserCategoryService;
import com.TBmail.EmailService.Service.UserEmailService;
import com.TBmail.EmailService.Service.UserService;

@EnableScheduling
@Component
@Service
public class ScheduledTask {

	@Autowired
	private NewsService newsService;

	@Autowired
	private LastSentService lastSentService;

	@Autowired
	private UserEmailService userEmailService;

	@Autowired
	private UserCategoryService userCategoryService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private Init init;

	@Autowired
	private EmailSenderService senderService;

	@Autowired
	private UserService userService;

	//@Scheduled(cron = "0 * * * * *") // "0 10/2 * * *"
	public void sendMail() {

		//init.initDb();
		List<UserResponse> data = userService.getAllUsersR();
		
		System.out.println("+-+-+-+-+-+-+-+");

		for (int i = 0; i < data.size(); i++) {

			UserCategoryResponse uC = userCategoryService.findByUserIdR(data.get(i).getId());
			String tag = uC.getNewsCategoryId().getCategoryUrl();
			ArrayList<String> urls = new ArrayList<String>();
			for (int j = 0; j < 20; j++) {
				String t1 = LastNews.getNewsUrl(MailContent.getHtml(tag));
				urls.add(t1);

			}
			UserEmailResponse userem = userEmailService.findByUserIdR(data.get(i).getId());
			LastSentResponse ls = lastSentService.findByUserEmailIdR(userem.getId());

			NewsResponse n = ls.getNewsId();

			String lastSent = n.getUrl();

			// System.out.println("last sent is "+lastSent);
			// System.out.println("last news in website is "+urls.get(0));
			if (!(urls.get(0).equals(lastSent))) {
				System.out.println("mail is being sent");
				ArrayList<String> mailUrls = new ArrayList<String>();
				int index = urls.indexOf(lastSent);
				System.out.println(index);
				if (index != -1) {
					LastNews.resetIndex();
					for (int j = index - 1; j >= 0; j--) {
						mailUrls.add(urls.get(j));
					}
				} else {
					for (int j = 0; j < urls.size(); j++) {
						mailUrls.add(urls.get(j));
					}
				}
				for (int j = 0; j < mailUrls.size(); j++) {
					System.out.println("Following news will be sent: " + mailUrls.get(j));
					String content = MailContent.getContent(mailUrls.get(j));

					String emailId = userEmailService.findByUserIdR(data.get(i).getId()).getEmailId().getId();

					EmailResponse emailResponse = emailService.findById(emailId);

					// senderService.sendEmail(emailResponse.getEMail(),"TB özet", content);
					// //mailUrls.get(j)

					News news = new News();
					news.setContent(MailContent.getContent(mailUrls.get(j)));
					news.setUrl(mailUrls.get(j));

					String page = MailContent.getHtml(mailUrls.get(j));

					String title = GetHead.getHead(page);
					news.setTitle(title);

					String postDate = LastNews.getLastNewsTime(mailUrls.get(j));

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(postDate, formatter);
					ZoneId gmtPlus3 = ZoneId.of("GMT+3");
					ZonedDateTime zonedDateTime = localDateTime.atZone(gmtPlus3);
					LocalDateTime dateTime = zonedDateTime.toLocalDateTime();
					news.setPostDate(dateTime);
					news.setCategoryId(uC.getNewsCategoryId());

					NewsResponse newNews = newsService.addNewsR(news);
					

					if (j == mailUrls.size() - 1) {
						UserEmailResponse eRes = userEmailService.findByUserIdR(data.get(i).getId());

						LastSentResponse url = lastSentService.findByUserEmailIdR(eRes.getId());

						url.setNewsId(newNews);
						LastSent lsAdd = new LastSent();
						lsAdd.setId(url.getId());
						lsAdd.setLastSentId(url.getLastSentId());
						NewsResponse nr = url.getNewsId();
						News update = new News();
						update.setId(nr.getId());
						update.setNewsId(nr.getNewsId());
						update.setUrl(nr.getUrl());
						update.setTitle(nr.getTitle());
						update.setCategoryId(nr.getCategoryId());
						update.setPostDate(nr.getPostDate());
						update.setContent(nr.getContent());

						lsAdd.setNewsId(update);

						UserEmail userEmail = new UserEmail();
						userEmail.setEmailId(eRes.getEmailId());
						userEmail.setId(eRes.getId());
						userEmail.setUserEmailId(eRes.getUserEmailId());
						userEmail.setUserEmailId(eRes.getUserEmailId());

						lsAdd.setUserEmailId(userEmail);

						lastSentService.addLastSentR(lsAdd);

					}

				}

			} else
				System.out.println("New news is not found");

			LastNews.resetIndex();
		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Waiting...");
		System.out.println("current date and time is: " + dtf.format(now));

		System.out.println("++++++++++++++++++++++++++++++++++++");

	}

	/*
	 * public <T> T getDataFromAPI(String apiUrl, ParameterizedTypeReference<T>
	 * responseType) { ResponseEntity<T> responseEntity =
	 * restTemplate.exchange(apiUrl, HttpMethod.GET, null, responseType); return
	 * responseEntity.getBody(); 
	 * }
	 */

}

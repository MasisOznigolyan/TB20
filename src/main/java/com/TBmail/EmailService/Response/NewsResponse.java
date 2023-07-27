package com.TBmail.EmailService.Response;

import java.time.LocalDateTime;

import com.TBmail.EmailService.Collections.NewsCategory;
import com.TBmail.EmailService.Collections.Uid;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class NewsResponse {
	
    private String id;
	
	
    private String newsId;
	
	
	private String url;
	
	
    private String title;
	
	
    private String content;
	
    private LocalDateTime postDate;
	
    private NewsCategory categoryId;

	public NewsResponse() {
		this.newsId=Uid.generateUniqueId();
	}
	public NewsResponse(String url, String title, String content, LocalDateTime postDate, NewsCategory categoryId ) {
		this.newsId=Uid.generateUniqueId();
		this.content=content;
		this.url=url;
		this.title=title;
		this.postDate=postDate;
		this.categoryId=categoryId;
		
	}
	@Override
	public String toString() {
		return "NewsResponse [id=" + id + ", newsId=" + newsId + ", url=" + url + ", postDate=" + postDate
				+ ", categoryId=" + categoryId + "]";
	}
	
}

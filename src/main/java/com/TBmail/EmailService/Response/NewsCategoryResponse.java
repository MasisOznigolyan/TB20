package com.TBmail.EmailService.Response;

import com.TBmail.EmailService.Collections.Uid;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class NewsCategoryResponse {
	
    private String id;
		
	
    private String newsCategoryId;
	
	
    private String name;
	
	
    private String categoryUrl;

	public NewsCategoryResponse() {
		this.newsCategoryId=Uid.generateUniqueId();
	}

	@Override
	public String toString() {
		return "NewsCategoryResponse [id=" + id + ", newsCategoryId=" + newsCategoryId + ", name=" + name
				+ ", categoryUrl=" + categoryUrl + "]";
	}
}
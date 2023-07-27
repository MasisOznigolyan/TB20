package com.TBmail.EmailService.Response;

import com.TBmail.EmailService.Collections.Uid;
import com.TBmail.EmailService.Collections.UserEmail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class LastSentResponse {
	
    private String id;
	
	private String LastSentId;
	
	
	private UserEmail userEmailId;
	
	
	private NewsResponse newsId;
	
	public LastSentResponse() {
		this.LastSentId=Uid.generateUniqueId();
	}

	@Override
	public String toString() {
		return "LastSentResponse [id=" + id + ", LastSentId=" + LastSentId + ", userEmailId=" + userEmailId
				+  "]";
	}
	
	
}

package com.TBmail.EmailService.Response;

import com.TBmail.EmailService.Collections.UserEmail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
@AllArgsConstructor
@NoArgsConstructor
public class LastSentResponse {
	
    private String id;
	
	private String LastSentId;
	
	
	private UserEmail userEmailId;
	
	
	private NewsResponse newsId;
	
	

	@Override
	public String toString() {
		return "LastSentResponse [id=" + id + ", LastSentId=" + LastSentId + ", userEmailId=" + userEmailId
				+  "]";
	}
	
	
}

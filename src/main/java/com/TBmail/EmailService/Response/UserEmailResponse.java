package com.TBmail.EmailService.Response;

import com.TBmail.EmailService.Collections.Email;
import com.TBmail.EmailService.Collections.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class UserEmailResponse {
	
    private String id;
		

	private String userEmailId;
	
	
	private User userId;
	
	
	private Email emailId;
	
	@Override
	public String toString() {
		return "UserEmailResponse [id=" + id + ", userEmailId=" + userEmailId + ", userId=" + userId + ", emailId="
				+ emailId + "]";
	}

	
}

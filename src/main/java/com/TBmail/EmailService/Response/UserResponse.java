package com.TBmail.EmailService.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class UserResponse {
	private String id;
    private String userId;
	
	private String name;

	@Override
	public String toString() {
		return "UserResponse [id="+ id+ ", userId=" + userId + ", name=" + name + "]";
	}
	
}

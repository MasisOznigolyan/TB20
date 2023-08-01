package com.TBmail.EmailService.Response;

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
public class UserResponse {
	private String id;
    private String userId;
	
	private String name;

	@Override
	public String toString() {
		return "UserResponse [id="+ id+ ", userId=" + userId + ", name=" + name + "]";
	}
	
}

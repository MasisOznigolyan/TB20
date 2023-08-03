package com.TBmail.EmailService.Response;

import com.TBmail.EmailService.Collections.NewsCategory;
import com.TBmail.EmailService.Collections.User;

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
public class UserCategoryResponse {
	
	    private String id;


		private String userCategoryId;
		
	
		private User userId;
		
		
		private NewsCategory newsCategoryId;
		
		@Override
		public String toString() {
			return "UserCategory [id=" + id + ", userCategoryId=" + userCategoryId + ", userId=" + userId
					+ ", newsCategoryId=" + newsCategoryId + "]";
		}
}

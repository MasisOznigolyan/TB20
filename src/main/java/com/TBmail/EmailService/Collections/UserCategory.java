package com.TBmail.EmailService.Collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
@Document(collection="UserCategory")
public class UserCategory {
	@NonNull
    @Id
    @Setter(AccessLevel.NONE)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
	
	@NonNull
    @Field("userCategoryId")
	@Setter(AccessLevel.NONE)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String userCategoryId;
	
	@DBRef
	@Field("userId")
	private User userId;
	
	@DBRef
	@Field("newsCategoryId")
	private NewsCategory newsCategoryId;
	public UserCategory() {
		this.userCategoryId=Uid.generateUniqueId();
	}
}

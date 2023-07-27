package com.TBmail.EmailService.Collections;

import org.springframework.data.annotation.Id;
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
@Document(collection="NewsCategory")
public class NewsCategory {
	@NonNull
    @Id
    @Setter(AccessLevel.NONE)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
		
	@NonNull
    @Field("newsCategoryId")
	@Setter(AccessLevel.NONE)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String newsCategoryId;
	
	@NonNull
    @Field("name")
    private String name;
	
	@NonNull
    @Field("categoryUrl")
    private String categoryUrl;

	public NewsCategory() {
		this.newsCategoryId=Uid.generateUniqueId();
	}
}

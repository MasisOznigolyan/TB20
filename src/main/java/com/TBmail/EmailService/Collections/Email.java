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
@Document(collection = "Email")
public class Email {


	@NonNull
    @Id
    @Setter(AccessLevel.NONE)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
		
	@NonNull
    @Field("eMailId")
	@Setter(AccessLevel.NONE)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String eMailId;
	
	
	 @Field("eMail")
	 private String eMail;
	 
	 
	 
	 public Email(){
		 this.eMailId=Uid.generateUniqueId();
	 }
	 
	 
	 
}

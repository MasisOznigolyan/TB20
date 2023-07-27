package com.TBmail.EmailService.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class EmailResponse {
	
    //private String id;
		
    private String eMailId;
	
	private String eMail;
}

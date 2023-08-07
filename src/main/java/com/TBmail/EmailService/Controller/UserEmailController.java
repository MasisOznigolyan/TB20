package com.TBmail.EmailService.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TBmail.EmailService.Collections.UserEmail;
import com.TBmail.EmailService.Response.UserEmailResponse;
import com.TBmail.EmailService.Service.UserEmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name="User-Email", description="Controller for user-email CRUD operations")
@RestController
public class UserEmailController {

	@Autowired
	UserEmailService userEmailService;
	
	private static final Logger logInfo=LoggerFactory.getLogger(NewsCategoryController.class);
	
	@Operation(summary="get userEmail by primary user id",
			description="Get the userEmail info by spesifying primary user id. Response will be userEmailResponse object.")
	@GetMapping("/userEmail/{UserId}")
	public ResponseEntity<UserEmailResponse> findByUserId(@PathVariable("UserId") String id ){
		UserEmailResponse ue=userEmailService.findByUserIdR(id);
		logInfo.info("UserEmail is retrived with user id of "+id);
		return ResponseEntity.status(HttpStatus.OK).body(ue);
	}
	
	
	@Operation(summary="delete all userEmail",
			description="deletes all the userEmail from database. Do not use if you don't have database backup")
	@DeleteMapping("/userEmail/delete")
	public ResponseEntity<Void> deleteUserEmails(){
		boolean deleted =userEmailService.deleteAllUserEmailR();
		if(deleted) {
			logInfo.info("All UserEmails are deleted");
			return ResponseEntity.status(HttpStatus.GONE).build();
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
	@Operation(summary="create/update userEmail",
			description="create/update by spesifying userEmail body. Response will be UserEmailResponse object")
	@PostMapping("/userEmail/create")
	public ResponseEntity<UserEmailResponse> addUserEmail(UserEmail userEmail){
		UserEmailResponse ue=userEmailService.addUserEmailR(userEmail);
		logInfo.info("new userEmail has been created");
		return ResponseEntity.status(HttpStatus.CREATED).body(ue);
	}
	
}

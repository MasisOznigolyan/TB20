package com.TBmail.EmailService.Test.Tests;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.TBmail.EmailService.Collections.UserEmail;
import com.TBmail.EmailService.Controller.UserEmailController;
import com.TBmail.EmailService.Response.UserEmailResponse;
import com.TBmail.EmailService.Test.Generator.UserEmailGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserEmailController.class)
public class UserEmailTests {
	
	private final static String CONTENT_TYPE = "application/json";
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private UserEmailController userEmailController;
	
	@MockBean
	private ModelMapper modelMapper;
	
	private UserEmailGenerator userEmailGenerator= new UserEmailGenerator();
	
	@Test
	@DisplayName("getUserEmailById")
	public void getUserEmailById() throws Exception{
		UserEmail ue=userEmailGenerator.generateUserEmail();
		UserEmailResponse ueR= new UserEmailResponse(ue.getId(),ue.getUserEmailId(),ue.getUserId(),ue.getEmailId());
		
		when(userEmailController.findByUserId(ue.getUserEmailId()))
		.thenReturn(ResponseEntity.status(HttpStatus.OK).body(ueR));
		
		mockMvc.perform(get("/userEmail/"+ue.getUserEmailId()).accept(CONTENT_TYPE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id",is(ue.getId())));
			
	}
	
	@Test
	@DisplayName("deleteAllUserEmailTest")
	public void deleteAllUserEmailTest() throws Exception {
		when(userEmailController.deleteUserEmails())
		.thenReturn(ResponseEntity.status(HttpStatus.GONE).build());
	
	mockMvc.perform(delete("/userEmail/delete"))
		.andExpect(status().is(410))
		.andReturn();
	}
	
	
	@Test
	@DisplayName("createUserEmail")
	public void createUserEmail() throws Exception {
		UserEmail userEmail=userEmailGenerator.generateUserEmail();
		
		UserEmailResponse uR= modelMapper.map(userEmail, UserEmailResponse.class);
		when(userEmailController.addUserEmail(userEmail))
			.thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(uR));
		
		ObjectMapper objectMapper = new ObjectMapper();
		String requestJson = objectMapper.writeValueAsString(userEmail);
		
		mockMvc.perform(post("/userEmail/create")
			.contentType(CONTENT_TYPE).content(requestJson))
        	.andExpect(status().isCreated())
        	.andReturn();
	}
}

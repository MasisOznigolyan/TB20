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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.TBmail.EmailService.Collections.Email;
import com.TBmail.EmailService.Controller.EmailController;
import com.TBmail.EmailService.Response.EmailResponse;
import com.TBmail.EmailService.Test.Generator.EmailGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(EmailController.class)
public class EmailTests {

private final static String CONTENT_TYPE = "application/json";
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private EmailController emailController;
	
	

	

	@MockBean
	private ModelMapper modelMapper;
	
	EmailGenerator emailGenerator= new EmailGenerator();
	
	@Test
    @DisplayName("getAllEmailsTest")
	public void getAllEmailsTest() throws Exception {
		EmailResponse EmailRes= emailGenerator.generateEmailResponse();
        
        when(emailController.getEmailById(EmailRes.getEMailId())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(EmailRes));
        MvcResult result = mockMvc.perform(get("/emails/"+EmailRes.getEMailId()).accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email",is(EmailRes.getEMail())))
                .andReturn();
	}
	
	@Test
    @DisplayName("tryToGetEmailWithWrongId")
	public void wrongId() throws Exception{
		EmailResponse EmailRes= emailGenerator.generateEmailResponse();
		when(emailController.getEmailById(EmailRes.getEMailId()+"abc")).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
		RequestBuilder request =MockMvcRequestBuilders.get("/emails/"+EmailRes.getEMailId()+"abc")
                .accept(CONTENT_TYPE);
		
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is(404))
                .andReturn();
	}
	
	@Test
	@DisplayName("deleteAllEmailsTest")
	public void deleteAllEmails() throws Exception {
		
		when(emailController.deleteAllEmails())
		.thenReturn(ResponseEntity.status(HttpStatus.GONE).build());
	
		mockMvc.perform(delete("/emails/delete"))
			.andExpect(status().is(410))
			.andReturn();
	}
	
	@Test
	@DisplayName("addNewEmail")
	public void addNewEmail() throws Exception {
		Email email= emailGenerator.generateEmail();
		ObjectMapper objectMapper = new ObjectMapper();
		String requestJson = objectMapper.writeValueAsString(email);
		//System.out.println("reas: "+requestJson);
		EmailResponse eRes=modelMapper.map(email, EmailResponse.class);
		//new EmailResponse(email.getId(),email.getEMailId(),email.getEMail());
		when(emailController.createEmail(email))
			.thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(eRes));
		
		
		
		mockMvc.perform(post("/emails/create")
				.contentType(CONTENT_TYPE).content(requestJson))
				.andExpect(status().isCreated())
				.andReturn();
		
		
	}
	
}

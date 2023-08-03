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

import com.TBmail.EmailService.Collections.LastSent;
import com.TBmail.EmailService.Controller.LastSentController;
import com.TBmail.EmailService.Response.LastSentResponse;
import com.TBmail.EmailService.Response.NewsResponse;
import com.TBmail.EmailService.Test.Generator.LastSentGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LastSentController.class)
public class LastSentTests {
	
	private final static String CONTENT_TYPE = "application/json";
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private LastSentController lastSentController;
	
	@MockBean
	private ModelMapper modelMapper;
	
	private LastSentGenerator lastSentGenerator= new  LastSentGenerator();
	
	@Test
	@DisplayName("getLastSentByUserEmailId")
	public void getLastSentByUserEmailId() throws Exception {
		LastSent ls=lastSentGenerator.generateLastSent();
		NewsResponse nr= modelMapper.map(ls.getNewsId(), NewsResponse.class);
		LastSentResponse lsr= new LastSentResponse(ls.getId(),ls.getLastSentId(),ls.getUserEmailId(),nr);
		
		when(lastSentController.findByUserEmail(ls.getUserEmailId().getId()))
			.thenReturn(ResponseEntity.status(HttpStatus.OK).body(lsr));
		
		mockMvc.perform(get("/lastSent/"+ls.getUserEmailId().getId()).accept(CONTENT_TYPE))
		.andExpect(status().isOk())
        
        .andExpect(jsonPath("$.id",is(ls.getId())))
        
        .andReturn();
	}
	
	@Test
	@DisplayName("deleteAllLastSent")
	public void deleteAllLastSent() throws Exception {
		when(lastSentController.deleteAllLastSent())
		.thenReturn(ResponseEntity.status(HttpStatus.GONE).build());
	
	mockMvc.perform(delete("/lastSent/delete"))
		.andExpect(status().is(410))
		.andReturn();
		
	}
	
	@Test
	@DisplayName("createLastSent")
	public void createLastSent() throws Exception {
		LastSent ls=lastSentGenerator.generateLastSent();
		NewsResponse nr= modelMapper.map(ls.getNewsId(), NewsResponse.class);
		LastSentResponse lsr= new LastSentResponse(ls.getId(),ls.getLastSentId(),ls.getUserEmailId(),nr);
		
		when(lastSentController.addLastSent(ls))
			.thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(lsr));
	
		
		
		mockMvc.perform(post("/lastSent/add")
				.contentType(CONTENT_TYPE).content(asJsonString(ls)))
	        	.andExpect(status().isCreated())
	        	.andReturn();
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	  
	}
}

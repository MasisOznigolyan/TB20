package com.TBmail.EmailService.Test.Tests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.TBmail.EmailService.Collections.NewsCategory;
import com.TBmail.EmailService.Controller.NewsCategoryController;
import com.TBmail.EmailService.Response.NewsCategoryResponse;
import com.TBmail.EmailService.Test.Generator.NewsCategoryGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(NewsCategory.class)
public class NewsCategoryTests {

	private final static String CONTENT_TYPE = "application/json";
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private NewsCategoryController newsCategoryController;
	
	@MockBean
	private ModelMapper modelMapper;
	
	NewsCategoryGenerator newsCategoryGenerator= new NewsCategoryGenerator();
	
	
	@Test
	@DisplayName("deleteAllUserCategoryTest")
	public void deleteNewsCategory() throws Exception {
		when(newsCategoryController.deleteAllNewsCategory())
		.thenReturn(ResponseEntity.status(HttpStatus.GONE).build());
	
	mockMvc.perform(delete("/NewsCategory/delete"))
		.andExpect(status().is(410))
		.andReturn();
	}
	
	@Test
	@DisplayName("addNewsCategoryTest")
	public void addNewsCategory() throws Exception {
		NewsCategory nC= newsCategoryGenerator.generateNewsCategory();
		NewsCategoryResponse ncR=modelMapper.map(nC, NewsCategoryResponse.class);
		when(newsCategoryController.addNewsCategory(nC))
		.thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(ncR));
		
		
		
		mockMvc.perform(post("/NewsCategory/add").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(nC)))
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




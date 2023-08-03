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

import com.TBmail.EmailService.Collections.News;
import com.TBmail.EmailService.Controller.NewsController;
import com.TBmail.EmailService.Response.NewsResponse;
import com.TBmail.EmailService.Test.Generator.NewsGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(NewsController.class)
public class NewsTest {

	private final static String CONTENT_TYPE = "application/json";
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private NewsController newsController;
	
	
	@MockBean
	private ModelMapper modelMapper;
	
	private NewsGenerator newsGenerator= new NewsGenerator();
	
	@Test
	@DisplayName("addNewsTest")
	public void addNews() throws Exception {
		News n=newsGenerator.generateNews();
		NewsResponse nR=modelMapper.map(n, NewsResponse.class);
		when(newsController.addNews(n)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(nR));
		
		mockMvc.perform(post("/News/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(n)))
        .andExpect(status().isCreated());
		
	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	  }
	
	@Test
	@DisplayName("deleteAllnewsTest")
	public void deleteAllNews() throws Exception {
		when(newsController.deleteAllNews()).thenReturn(ResponseEntity.status(HttpStatus.GONE).build() );
		mockMvc.perform(delete("/News/delete").accept(CONTENT_TYPE)).andExpect(status().is(410)).andReturn();
	}
	
}

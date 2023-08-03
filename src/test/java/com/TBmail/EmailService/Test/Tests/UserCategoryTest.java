package com.TBmail.EmailService.Test.Tests;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.TBmail.EmailService.Collections.UserCategory;
import com.TBmail.EmailService.Controller.UserCategoryController;
import com.TBmail.EmailService.Response.UserCategoryResponse;
import com.TBmail.EmailService.Test.Generator.UserCategoryGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserCategoryController.class)
public class UserCategoryTest {

private final static String CONTENT_TYPE = "application/json";
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private UserCategoryController userCategoryController;
	
	@MockBean
	private ModelMapper modelMapper;
	
	UserCategoryGenerator userCategoryGenerator= new UserCategoryGenerator();
	
	@Test
	@DisplayName("getUserCategoryByIdTest")
	public void getUserCategoryById() throws Exception {
		UserCategory uC= userCategoryGenerator.generateUserCategory();
		UserCategoryResponse ucR=new UserCategoryResponse(uC.getId(),uC.getUserCategoryId(),uC.getUserId(),uC.getNewsCategoryId());
				//modelMapper.map(uC,UserCategoryResponse.class);
		
		when(userCategoryController.findByUserId(uC.getUserId().getUserId()))
			.thenReturn(ResponseEntity.status(HttpStatus.OK).body(ucR));
		
		mockMvc.perform(get("/userCategory/"+uC.getUserId().getUserId())
			.accept(CONTENT_TYPE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id",is(uC.getId())));
	}
	
	@Test
	@DisplayName("DeleteAllUserCategoryTest")
	public void deleteAlluserCategory() throws Exception {
		when(userCategoryController.deleteAllUserCategory()).thenReturn(ResponseEntity.status(HttpStatus.GONE).build());
		
		mockMvc.perform(delete("/userCategory/delete").accept(CONTENT_TYPE))
			.andExpect(status().is(410));
	}
	
	@Test
	@DisplayName("createUserCategoryTest")
	public void createUserCategoryTest() throws Exception{
		UserCategory uC= userCategoryGenerator.generateUserCategory();
		UserCategoryResponse ucR=modelMapper.map(uC,UserCategoryResponse.class);
		when(userCategoryController.createUserCategory(uC))
			.thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(ucR));
		
		mockMvc.perform(delete("/userCategory/create").accept(CONTENT_TYPE).content(asJsonString(uC)))
		
			.andExpect(status().is(201));
	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	  }
}

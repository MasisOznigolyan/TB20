package com.TBmail.EmailService.Test.Tests;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import com.TBmail.EmailService.Collections.User;
import com.TBmail.EmailService.Controller.UserController;
import com.TBmail.EmailService.Response.UserResponse;
import com.TBmail.EmailService.Test.Generator.UserGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UsersTests {

	private final static String CONTENT_TYPE = "application/json";
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private UserController userController;
	
	@MockBean
	private ModelMapper modelMapper;
	
	private UserGenerator userGenerator= new UserGenerator();
	
	@Test
	@DisplayName("getUserByIdTest")
	public void getById() throws Exception {
		User user=userGenerator.generateUser();
		System.out.println(user);
		UserResponse uR =new UserResponse(user.getId(),user.getUserId(),user.getName()); 
		
		//UserResponse uR=userGenerator.generateUserResponse();
		System.out.println(uR);
		String id=uR.getUserId();
		
		when(userController.getUserDetails(user.getUserId())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(uR));
		
		MvcResult result = mockMvc.perform(get("/users/"+id).accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(uR.getName())))
                .andExpect(jsonPath("$.id",is(uR.getId())))
                .andExpect(jsonPath("$.userId",is(uR.getUserId())))
                .andReturn();
		
		
		//UserResponse retrive=new ObjectMapper().readValue(responseBody, new TypeReference<UserResponse>() {});
        //assertNotNull(responseBody);
        //assertEquals(uR.getId(),retrive.getId());
        //assertEquals(uR.getName(),retrive.getName());

	}
	
	@Test
    @DisplayName("getAllUsersTest")
    public void getAllUsersTest() throws Exception{

        List<UserResponse> uRList= new ArrayList<UserResponse>();
        for(int i=0; i<3; i++) {
            uRList.add(userGenerator.generateUserResponse());
        }
        
        when(userController.getAllUsers()).thenReturn(ResponseEntity.status(HttpStatus.OK).body(uRList));
        MvcResult result = mockMvc.perform(get("/users").accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        List<UserResponse> userResponseList = new ObjectMapper().readValue(responseBody, new TypeReference<List<UserResponse>>() {});
        assertNotNull(userResponseList);

        for(int i=0; i<uRList.size(); i++)
            assertEquals(uRList.get(i).getId(),userResponseList.get(i).getId());

    }
	@Test
    @DisplayName("tryToGetAllUsersWithWrongUrl")
	public void wrongUrl() throws Exception{
		RequestBuilder request =MockMvcRequestBuilders.get("/user")
                .accept(CONTENT_TYPE);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is(404))
                .andReturn();
	}
	
	
	
	@Test
	@DisplayName("tryGetUserByWrongIdTest")
	public void getByWrongId() throws Exception {
		
		when(userController.getUserDetails("wrogIDnumber")).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
			
		mockMvc.perform(get("/users/wrogIDnumber").accept(CONTENT_TYPE))
	                .andExpect(status().isNotFound())
	                .andReturn();
	}
	
	@Test
	@DisplayName("createNewUserTest")
	public void createNewUser() throws Exception {
		User user=userGenerator.generateUser();
		
		UserResponse uR= modelMapper.map(user, UserResponse.class);
		when(userController.createUser(user))
			.thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(uR));
		
		ObjectMapper objectMapper = new ObjectMapper();
		String requestJson = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/users/create")
			.contentType(CONTENT_TYPE).content(requestJson))
        	.andExpect(status().isCreated())
        	.andReturn();
	}

	@Test
	@DisplayName("DeleteAllUsersTest")
	public void deleteAllUsers() throws Exception{
		when(userController.deleteAllUsers())
			.thenReturn(ResponseEntity.status(HttpStatus.GONE).build());
		
		mockMvc.perform(delete("/users/delete"))
			.andExpect(status().is(410))
			.andReturn();
	}
	
	@Test
	@DisplayName("deleteUserByIdTest")
	public void deleteUserById() throws Exception{
		User user=userGenerator.generateUser();
		when(userController.deleteUserById(user.getUserId())).thenReturn(ResponseEntity.status(HttpStatus.GONE).build());
		mockMvc.perform(delete("/users/delete/"+user.getUserId()))
		.andExpect(status().is(410))
		.andReturn();
	}
	
	@Test
	@DisplayName("tryDeleteUserByWrongIdTest")
	public void deleteUserByWrongId() throws Exception{
		User user=userGenerator.generateUser();
		when(userController.deleteUserById(user.getUserId()+"111111")).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		mockMvc.perform(delete("/users/delete/"+user.getUserId()+"111111"))
		.andExpect(status().is(404))
		.andReturn();
	}
	
}

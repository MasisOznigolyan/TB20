package com.TBmail.EmailService.Test.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.TBmail.EmailService.Response.UserResponse;
import com.TBmail.EmailService.Test.Generator.UserGenerator;
import com.TBmail.EmailService.Test.TestControllers.UserControllerForTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserControllerForTest.class)
public class Tests {

	private final static String CONTENT_TYPE = "application/json";
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private UserControllerForTest userControllerForTest;
	
	@Test
    @DisplayName("getAllUsersTest")
    public void getAllUsersTest() throws Exception{

        UserGenerator userGenerator= new UserGenerator();

        List<UserResponse> uRList= new ArrayList<UserResponse>();

        for(int i=0; i<3; i++) {
            uRList.add(userGenerator.generateUser());
            System.out.println(uRList.get(i));

        }
        
        when(userControllerForTest.getAllUsers()).thenReturn(ResponseEntity.status(HttpStatus.OK).body(uRList));
        MvcResult result = mockMvc.perform(get("/users").accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        List<UserResponse> userResponseList = new ObjectMapper().readValue(responseBody, new TypeReference<List<UserResponse>>() {});
        assertNotNull(userResponseList);

        for(int i=0; i<uRList.size(); i++)
            assertEquals(uRList.get(i).getId(),userResponseList.get(i).getId());

    }
}

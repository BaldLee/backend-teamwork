package com.se418.userservice;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup(){
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}


	@WithMockUser(roles={"ADMIN"})
	@Test
	public void insertBook() throws  Exception {
		mvc.perform(post("/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"liu\",\"password\":\"one\"}"))
				.andExpect(status().isOk());
		mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		mvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		mvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}

package com.se418.bookservice;

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
public class BookServiceApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup(){
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}


	@Test
	public void getOneBook() throws Exception {
		mvc.perform(get("/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


	@Test
	public void getBooks() throws Exception {
		mvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@WithMockUser(roles={"ADMIN"})
	@Test
	public void insertBook() throws  Exception {
		mvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"author\":\"liu\",\"title\":\"one\"}"))
				.andExpect(status().isOk());
		String result = mvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		assertTrue(result.equals("[{\"id\":2,\"author\":\"liu\",\"title\":\"one\"}]"));
		result = mvc.perform(delete("/books/2"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		assertTrue(result.equals("delete succeed!"));
	}

	@Test
	public void insertBookFail() throws  Exception{
		mvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"author\":\"liu\",\"title\":\"one\"}"))
				.andExpect(status().isForbidden());
	}

	@WithMockUser(roles = {"ADMIN"})
	@Test
	public void updateBook() throws  Exception {
		mvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"author\":\"liu\",\"title\":\"one\"}"))
				.andExpect(status().isOk());
		String result = mvc.perform(put("/books/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"author\":\"liu\",\"title\":\"two\"}"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		assertTrue(result.equals("{\"id\":1,\"author\":\"liu\",\"title\":\"two\"}"));
		mvc.perform(delete("/books/1"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
	}


}

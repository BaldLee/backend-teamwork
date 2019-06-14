package com.se418.imageservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup(){
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void insertImage() throws Exception {
		MockMultipartFile firstFile = new MockMultipartFile("book", "filename.txt", "text/plain", "some xml".getBytes());
		mvc.perform(MockMvcRequestBuilders.multipart("/books/1").file(firstFile))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().is(200));
	}

}

package com.se418.ratingbookservice;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class RatingBookControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RatingBookController ratingBookController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(ratingBookController).build();
    }

    @Test
    public void givenNoMatchingUserId() throws Exception{
        mockMvc.perform(get("/rate")).andExpect(status().is4xxClientError());
    }

    @Test
    public void givenMatchingUserId() throws Exception {
    }
}
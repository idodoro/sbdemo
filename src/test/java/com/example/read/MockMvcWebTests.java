package com.example.read;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by lenovo on 2018/7/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MockMvcWebTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setMockMvc(){
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void bookList() throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/book/12")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.view().name("bookList")).
                andExpect(MockMvcResultMatchers.model().attributeExists("bookList")).
                andExpect(MockMvcResultMatchers.model().attribute(
                        "bookList", Matchers.is(Matchers.empty())))
 ;

    }

}

package com.example.read;

import com.example.read.entity.Reader;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
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
public class SecurityTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setMockMvc(){
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    @WithMockUser(username = "111",password = "111",roles = "READER1")
    public void homepage_authenticatedUser() throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/book/12")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.view().name("bookList")).
                andExpect(MockMvcResultMatchers.model().attributeExists("bookList")).
                andExpect(MockMvcResultMatchers.model().attribute(
                        "bookList", Matchers.is(Matchers.empty())));
    }

    @Test
    @WithUserDetails("11")
    public void homepage_authenticatedUser2() throws  Exception{

        Reader one =new Reader();
        one.setUsername("11");
        one.setFullname("11");
        one.setPassword("11");

        mockMvc.perform(MockMvcRequestBuilders.get("/book/11")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.view().name("bookList")).
                andExpect(MockMvcResultMatchers.model().attributeExists("bookList")).
                andExpect(MockMvcResultMatchers.model().attribute("bookList", Matchers.hasSize(1))).
                andExpect(MockMvcResultMatchers.model().attribute("bookList", Matchers.contains(Matchers.samePropertyValuesAs(one))));
    }

}

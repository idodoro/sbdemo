package com.example.read;

import com.example.read.entity.Book;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
//        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(SecurityContext.springSecurity()).build();
    }

    @Test
    public void bookList() throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/book/12")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.view().name("bookList")).
                andExpect(MockMvcResultMatchers.model().attributeExists("bookList")).
                andExpect(MockMvcResultMatchers.model().attribute(
                        "bookList", Matchers.is(Matchers.empty())));
    }

    @Test
    public void postBook() throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/book/reader11").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("booktitle","booktitle")
                .param("writer","writer")
                .param("reader","reader11")
                .param("price","12.34")
                .param("des","des")
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//        .andExpect(MockMvcResultMatchers.view().name("redirect:/book/reader11"));
        .andExpect(MockMvcResultMatchers.header().string("location", "/book/reader11"));

        Book one =new Book();
        one.setId(1L);
        one.setBooktitle("booktitle");
        one.setWriter("writer");
        one.setReader("reader11");
        one.setPrice(12.34);
        one.setDes("des");

        mockMvc.perform(MockMvcRequestBuilders.get("/book/reader11")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.view().name("bookList")).
                andExpect(MockMvcResultMatchers.model().attributeExists("bookList")).
                andExpect(MockMvcResultMatchers.model().attribute("bookList", Matchers.hasSize(1))).
                andExpect(MockMvcResultMatchers.model().attribute("bookList", Matchers.contains(Matchers.samePropertyValuesAs(one))));


    }

}

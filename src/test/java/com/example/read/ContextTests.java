package com.example.read;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lenovo on 2018/8/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContextTests {

    @Test
    public void contextClose(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ConfigTest.class);
//        ac.getBean();
        ac.close();//JavaEE开发的颠覆者 P24 ApplicationContext没有close()
    }
}

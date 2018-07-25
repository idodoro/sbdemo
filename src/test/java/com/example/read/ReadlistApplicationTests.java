package com.example.read;

import com.example.read.dao.ReaderRepository;
import com.example.read.entity.Reader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadlistApplicationTests {
	@Autowired
	private ReaderRepository readerRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testReaderRepository(){
		Reader reader = readerRepository.findById("11").orElse(null);

		System.out.println("###############");
		System.out.println(reader);
		System.out.println(reader.getUsername());
		System.out.println(reader.getUsername());
		System.out.println(reader.getPassword());
		System.out.println(reader.getAuthorities());
		Collection<? extends GrantedAuthority> authorities = reader.getAuthorities();

		for (GrantedAuthority auth:authorities) {
			System.out.println(auth);
		}
		System.out.println(reader);
		System.out.println("###############");
	}


}

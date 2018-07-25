package com.example.read.dao;

import com.example.read.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by lenovo on 2018/7/19.
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {
    //    Reader findOne(String username);
    Reader findByUsernameAndPassword(String username, String password);
}

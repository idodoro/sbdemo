package com.example.read.controller;

import com.example.read.dao.ReaderRepository;
import com.example.read.entity.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2018/7/19.
 */
@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping("/login")
    public String login(){
            return "login";
    }

    @PostMapping("/login")
    public String login(Reader reader, Model model,String error){
        Reader result = readerRepository.findByUsernameAndPassword(reader.getUsername(),reader.getPassword());
        if(error==null){
            if(result!=null){
                return "redirect:/book/"+reader.getUsername();
            }else {
                return "login";
            }
        }
        return "login";
    }

    @PostMapping("/register")
    public String setWriter( Reader reader){
        readerRepository.save(reader);
        return "login";
    }
}

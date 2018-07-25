package com.example.read.controller;

import com.example.read.dao.ReadingListRepository;
import com.example.read.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by lenovo on 2018/7/19.
 */
@Controller
@RequestMapping("/")
public class BookController {
    @Autowired
    private ReadingListRepository readingListRepository;

    @GetMapping("/book/{reader}")
    public String getByWriter(@PathVariable String reader, Model model){
        List<Book> bookList = readingListRepository.findByReader(reader);
        if(bookList!=null){
            model.addAttribute("bookList",bookList);
        }
        return "bookList";
    }

    @PostMapping("/book/{reader}")
    public String setWriter(@PathVariable String reader, Book book){
//        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/book/"+book.getReader();
//        return "redirect:/book/{reader}";//单元测试无法识别{reader}
    }
}

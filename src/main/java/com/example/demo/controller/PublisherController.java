package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.model.Publisher;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api")
public class PublisherController {

	@Autowired
	BookService bookService;
	
	 @PostMapping("/publishers")
	    public Publisher addPublisher(@RequestBody Publisher publisher) {
	        return bookService.createPublisher(publisher.getName());
	    }

	    @PostMapping("/books")
	    public Book addBook(@RequestBody Map<String, Object> payload) {
	        String title = (String) payload.get("title");
	        String author = (String) payload.get("author");
	        Long publisherId = Long.valueOf(payload.get("publisherId").toString());
	        return bookService.createBookWithPublisher(title, author, publisherId);
	    }

	    @GetMapping("/publishers/{id}/books")
	    public List<Book> getBooksByPublisher(@PathVariable Long id) {
	        return bookService.getBooksByPublisher(id);
	    }}

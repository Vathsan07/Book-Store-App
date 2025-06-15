package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.Publisher;
import com.example.demo.repository.BookRepo;
import com.example.demo.repository.PublisherRepo;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepository;

    @Autowired
    private PublisherRepo publisherRepository;

    public Book createBookWithPublisher(String title, String author, Long publisherId) {
        Publisher publisher = publisherRepository.findById(publisherId).orElseThrow();
        Book book = new Book(title, author, publisher);
        return bookRepository.save(book);
    }

    public Publisher createPublisher(String name) {
        Publisher publisher = new Publisher(name);
        return publisherRepository.save(publisher);
    }

    public List<Book> getBooksByPublisher(Long publisherId) {
        Publisher publisher = publisherRepository.findById(publisherId).orElseThrow();
        return publisher.getBooks();
    }
}

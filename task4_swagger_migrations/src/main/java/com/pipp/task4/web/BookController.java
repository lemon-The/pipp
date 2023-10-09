package com.pipp.task4.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pipp.task4.domain.Author;
import com.pipp.task4.domain.Book;

@RestController
public class BookController {
  private BookService service;

  @Autowired
  public BookController(BookService service) {
    this.service = service;
  }

  @GetMapping
  public List<Book> getAllBooks() {
    return service.getAllBooks();
  }

  @GetMapping
  public List<Author> getAllAuthors() {
    return service.getAllAuthors();
  }
}

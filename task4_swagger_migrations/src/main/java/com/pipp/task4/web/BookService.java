package com.pipp.task4.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pipp.task4.data.AuthorRepository;
import com.pipp.task4.data.BookRepository;
import com.pipp.task4.domain.Author;
import com.pipp.task4.domain.Book;

@Service
public class BookService {
  private BookRepository bookRepo;
  private AuthorRepository authorRepo;

  @Autowired
  public BookService(BookRepository bookRepo, AuthorRepository authorRepo) {
    this.bookRepo = bookRepo;
    this.authorRepo = authorRepo;
  }

  public List<Book> getAllBooks() {
    return bookRepo.findAll();
  }

  public List<Author> getAllAuthors() {
    return authorRepo.findAll();
  }
}

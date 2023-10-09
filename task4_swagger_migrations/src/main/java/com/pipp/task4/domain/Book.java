package com.pipp.task4.domain;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
  @Id
  private Long id;

  private String title;
  private LocalDate date;

  @ManyToOne
  private Author author;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, date, author);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Book other = (Book) obj;
    return Objects.equals(title, other.title) && Objects.equals(date, other.date)
        && Objects.equals(author, other.author);
  }

  @Override
  public String toString() {
    return "Book[title=" + title + ", date=" + date + ", author=" + author + "]";
  }
}

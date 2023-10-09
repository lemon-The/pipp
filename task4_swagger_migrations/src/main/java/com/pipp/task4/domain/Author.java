package com.pipp.task4.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Author {
  @Id
  private Long id;

  private String firstName;
  private String surName;
  private LocalDate birthDate;

  private List<Book> writtenBooks;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSurName() {
    return surName;
  }

  public void setSurName(String surName) {
    this.surName = surName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public List<Book> getWrittenBooks() {
    return writtenBooks;
  }

  public void setWrittenBooks(List<Book> writtenBooks) {
    this.writtenBooks = writtenBooks;
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, surName, birthDate, writtenBooks);
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
    Author other = (Author) obj;
    return Objects.equals(firstName, other.firstName) && Objects.equals(surName, other.surName)
        && Objects.equals(birthDate, other.birthDate) && Objects.equals(writtenBooks, other.writtenBooks);
  }

  @Override
  public String toString() {
    return "Author[firstName=" + firstName + ", surName=" + surName + ", birthDate=" + birthDate + ", writtenBooks="
        + writtenBooks + "]";
  }
}

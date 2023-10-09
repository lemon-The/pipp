package com.pipp.task4.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pipp.task4.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}

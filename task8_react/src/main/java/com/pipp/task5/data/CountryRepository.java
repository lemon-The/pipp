package com.pipp.task5.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pipp.task5.pojo.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
  @Query("SELECT c FROM Country c WHERE c.name = ?1")
  Optional<Country> findByName(String name);
}

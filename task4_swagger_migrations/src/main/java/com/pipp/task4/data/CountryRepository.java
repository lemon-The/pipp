package com.pipp.task4.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pipp.task4.pojo.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}

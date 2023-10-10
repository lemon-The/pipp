package com.pipp.task4.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pipp.task4.pojo.Warship;

public interface WarshipRepository extends JpaRepository<Warship, Long> {

}

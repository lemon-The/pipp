package com.pipp.task4.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pipp.task4.pojo.Battle;
import com.pipp.task4.pojo.Warship;

@Repository
public interface WarshipRepository extends JpaRepository<Warship, Long> {
  //@Query("SELECT w.battles FROM Warship w WHERE w.:d = ?1")
  //List<Battle> findBattlesForWarship(Long id);
}

package com.pipp.task4.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pipp.task4.pojo.Battle;
import com.pipp.task4.pojo.Warship;

public interface BattleRepository extends JpaRepository<Battle, Long> {
  @Query("select bm.member from BattleMember bm where bm.battle.id = ?1")
  List<Warship> findWarships(Long id);
}

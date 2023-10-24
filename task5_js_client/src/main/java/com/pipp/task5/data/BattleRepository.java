package com.pipp.task5.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pipp.task5.pojo.Battle;
import com.pipp.task5.pojo.Warship;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {
  //@Query("select bm.member from BattleMember bm where bm.battle.id = ?1")
  //List<Warship> findWarships(Long id);
}

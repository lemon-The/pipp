//package com.pipp.task4.data;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.pipp.task4.pojo.BattleMember;
//
//@Repository
//public interface BattleMemberRepository extends JpaRepository<BattleMember, Long> {
//  @Query("SELECT bm FROM BattleMember bm WHERE bm.battle.name = ?1")
//  List<BattleMember> findByBattleName(String battleName);
//}

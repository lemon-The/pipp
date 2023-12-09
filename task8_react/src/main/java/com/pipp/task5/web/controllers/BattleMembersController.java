//package com.pipp.task5.web.controllers;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.pipp.task5.pojo.BattleMember;
//import com.pipp.task5.web.services.BattleMemberService;
//import com.pipp.task5.web.services.BattleService;
//import com.pipp.task5.web.services.WarshipService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/battle_members")
//public class BattleMembersController {
//
//  private BattleMemberService battleMemberService;
//  private BattleService battleService;
//  private WarshipService warshipService;
//  private Logger log;
//
//  @Autowired
//  public BattleMembersController(BattleMemberService battleMemberService,
//      BattleService battleService,
//      WarshipService warshipService) {
//    this.battleMemberService = battleMemberService;
//    this.battleService = battleService;
//    this.warshipService = warshipService;
//
//    this.log = LoggerFactory.getLogger(BattleMembersController.class);
//  }
//
//  //@ModelAttribute(name = "newBattleMember")
//  //public BattleMember newBattleMember() {
//  //  return new BattleMember();
//  //}
//
//  //@ModelAttribute(name = "allBattles")
//  //public List<Battle> allBattles() {
//  //  return battleService.findAll();
//  //}
//
//  //@ModelAttribute(name = "warships")
//  //public List<Warship> allWarships() {
//  //  return warshipService.findAll();
//  //}
//
//  //@ModelAttribute(name = "battleMembers")
//  //public List<BattleMember> allBattleMembers() {
//  //  return battleMemberService.findAll();
//  //}
//
//  @GetMapping("/")
//  public List<BattleMember> getBattleMembers() {
//    return battleMemberService.findAll();
//  }
//
//  @GetMapping("/{id}")
//  public BattleMember getBattleMember(@PathVariable("id") Long id) {
//    return battleMemberService.find(id);
//  }
//
//  @GetMapping("/delete/{id}")
//  public void deleteBattleMemberById(@PathVariable("id") Long id) {
//    battleMemberService.delete(id);
//  }
//
//  //@GetMapping("/edit/{id}")
//  //public String editBattleMemberById(@PathVariable("id") Long id, Model model) {
//  //  BattleMember editBattleMember = battleMemberService.find(id);
//  //  log.info(editBattleMember.toString());
//  //  model.addAttribute("editBattleMember", editBattleMember);
//  //  return "edit_battle_member";
//  //}
//
//  @PostMapping("/update/{id}")
//  public void updateBattleMemberById(@PathVariable("id") Long id,
//      @Valid @RequestBody BattleMember editBattleMember,
//      Errors errors) {
//    if (!battleMemberService.isInputValid(editBattleMember, errors)) {
//      // TODO
//    }
//    editBattleMember.setId(id);
//    battleMemberService.update(editBattleMember);
//  }
//
//  @PostMapping("/save")
//  public void saveNewBattleMember(
//      @Valid @RequestBody BattleMember newBattleMember,
//      Errors errors) {
//    if (!battleMemberService.isInputValid(newBattleMember, errors)) {
//      // TODO
//    }
//    battleMemberService.save(newBattleMember);
//  }
//
//}

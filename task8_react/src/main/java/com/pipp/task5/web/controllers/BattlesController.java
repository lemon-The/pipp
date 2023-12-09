package com.pipp.task5.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pipp.task5.pojo.Battle;
import com.pipp.task5.web.services.BattleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/battles")
@CrossOrigin
public class BattlesController {

  private BattleService battleService;
  private Logger log;

  @Autowired
  public BattlesController(BattleService service) {
    this.battleService = service;
    this.log = LoggerFactory.getLogger(BattlesController.class);
  }

  //@ModelAttribute(name = "newBattle")
  //public Battle newBattle() {
  //  return new Battle();
  //}

  //@ModelAttribute(name = "battles")
  //public List<Battle> allBattes() {
  //  return battleService.findAll();
  //}

  @GetMapping("/")
  public List<Battle> getBattles() {
    return battleService.findAll();
  }

  @GetMapping("/{id}")
  public Battle getBattle(@PathVariable("id") Long id) {
    return battleService.find(id);
  }

  @GetMapping("/delete/{id}")
  public void deleteBattleById(@PathVariable("id") Long id) {
    battleService.delete(id);
  }

  //@GetMapping("/edit/{id}")
  //public String editBattleById(@PathVariable("id") Long id, Model model) {
  //  Battle editBattle = battleService.find(id);
  //  model.addAttribute("editBattle", editBattle);
  //  return "edit_battle";
  //}

  @PostMapping("/update/{id}")
  public void updateBattleById(@PathVariable("id") Long id,
      @Valid @RequestBody Battle editBattle,
      Errors errors, Model model) {
    if (!battleService.isInputValid(editBattle, errors)) {
      // TODO
    }
    editBattle.setId(id);
    battleService.update(editBattle);
  }

  @PostMapping("/save")
  public void saveNewBattle(
      @Valid @RequestBody Battle newBattle,
      Errors errors) {
    if (!battleService.isInputValid(newBattle, errors)) {
      // TODO
    }
    battleService.save(newBattle);
  }

}

package com.lemonthe.seabattles.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemonthe.seabattles.pojo.Battle;
import com.lemonthe.seabattles.web.services.BattleService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/battles")
public class BattlesController {

	private BattleService battleService;
	private Logger log;

	@Autowired
	public BattlesController(BattleService service) {
		this.battleService = service;
		this.log = LoggerFactory.getLogger(BattlesController.class);
	}

	@ModelAttribute(name = "newBattle")
	public Battle newBattle() {
		return new Battle();
	}

	@ModelAttribute(name = "battles")
	public List<Battle> allBattes() {
		return battleService.findAll();
	}

	@GetMapping
	public String getBattles() {
		return "battles";
	}
	
	@GetMapping("/delete/{id}")
    public String deleteBattleById(@PathVariable("id") Long id) {
        battleService.delete(id);
        return "redirect:/battles";
    }

	@GetMapping("/edit/{id}")
	public String editBattleById(@PathVariable("id") Long id, Model model) {
		Battle editBattle = battleService.find(id);
		model.addAttribute("editBattle", editBattle);
		return "edit_battle";
	}

	@PostMapping("/update/{id}")
    public String updateBattleById(@PathVariable("id") Long id,
            @Valid @ModelAttribute("editBattle") Battle editBattle,  
            Errors errors, Model model) {
	if (!battleService.isInputValid(editBattle, errors)) {
			return "edit_battle";
		}
        editBattle.setId(id);
		battleService.update(editBattle);

        return "redirect:/battles";
    }

	@PostMapping("/save")
	public String saveNewBattle(
            @Valid @ModelAttribute("newBattle") Battle newBattle,
            Errors errors) {
		if (!battleService.isInputValid(newBattle, errors)) {
			return "battles";
		} 
        battleService.save(newBattle);
        return "redirect:/battles";
    }

	
}

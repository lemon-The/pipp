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
import com.lemonthe.seabattles.pojo.BattleMember;
import com.lemonthe.seabattles.pojo.Warship;
import com.lemonthe.seabattles.web.services.BattleMemberService;
import com.lemonthe.seabattles.web.services.BattleService;
import com.lemonthe.seabattles.web.services.WarshipService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/battle_members")
public class BattleMembersController {

	private BattleMemberService battleMemberService;
	private BattleService battleService;
	private WarshipService warshipService;
	private Logger log;

	@Autowired
	public BattleMembersController(BattleMemberService battleMemberService, BattleService battleService, WarshipService warshipService) {
		this.battleMemberService = battleMemberService;
		this.battleService = battleService;
		this.warshipService = warshipService;

		this.log = LoggerFactory.getLogger(BattleMembersController.class);
	}

	@ModelAttribute(name = "newBattleMember")
	public BattleMember newBattleMember() {
		return new BattleMember();
	}

	@ModelAttribute(name = "allBattles")
	public List<Battle> allBattles() {
		return battleService.findAll();
	}

	@ModelAttribute(name = "warships")
	public List<Warship> allWarships() {
		return warshipService.findAll();
	}

	@ModelAttribute(name = "battleMembers")
	public List<BattleMember> allBattleMembers() {
		return battleMemberService.findAll();
	}

	@GetMapping
	public String getBattleMembers() {
		return "battle_members";
	}
	
	@GetMapping("/delete/{id}")
    public String deleteBattleMemberById(@PathVariable("id") Long id) {
        battleMemberService.delete(id);
        return "redirect:/battle_members";
    }

	@GetMapping("/edit/{id}")
	public String editBattleMemberById(@PathVariable("id") Long id, Model model) {
		BattleMember editBattleMember = battleMemberService.find(id);
		log.info(editBattleMember.toString());
		model.addAttribute("editBattleMember", editBattleMember);
		return "edit_battle_member";
	}

	@PostMapping("/update/{id}")
    public String updateBattleMemberById(@PathVariable("id") Long id,
            @Valid @ModelAttribute("editBattleMember") BattleMember editBattleMember,  
            Errors errors, Model model) {
		if (!battleMemberService.isInputValid(editBattleMember, errors)) {
			return "edit_battle_member";	
		}
        editBattleMember.setId(id);
		battleMemberService.update(editBattleMember);

        return "redirect:/battle_members";
    }

	@PostMapping("/save")
	public String saveNewBattleMember(
            @Valid @ModelAttribute("newBattleMember") BattleMember newBattleMember,
            Errors errors) {
		if (!battleMemberService.isInputValid(newBattleMember, errors)) {
			return "battle_members";	
		}
        battleMemberService.save(newBattleMember);
        return "redirect:/battle_members";
    }
	
}

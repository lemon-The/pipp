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

import com.lemonthe.seabattles.pojo.Country;
import com.lemonthe.seabattles.pojo.Warship;
import com.lemonthe.seabattles.web.services.CountryService;
import com.lemonthe.seabattles.web.services.WarshipService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/warships")
public class WarshipsController {

	private WarshipService warshipService;
	private CountryService countryService;
	private Logger log;

	@Autowired
	public WarshipsController(WarshipService warshipService,
			CountryService countryService) {
		this.warshipService = warshipService;
		this.countryService = countryService;
		log = LoggerFactory.getLogger(WarshipsController.class);
	}

	@ModelAttribute(name = "newWarship")
	public Warship newWarship() {
		return new Warship();
	}

	@ModelAttribute(name = "countries")
	public List<Country> allCountries() {
		return countryService.findAll();
	}

	@ModelAttribute(name = "warships")
	public List<Warship> allWarships() {
		return warshipService.findAll();
	}

	@GetMapping
	public String getAlltWarships() {
		return "warships";
	}

	@GetMapping("/delete/{id}")
    public String deleteWarshipByName(@PathVariable("id") Long id) {
        warshipService.delete(id);
        return "redirect:/warships";
    }

	@GetMapping("/edit/{id}")
	public String editWarshipById(@PathVariable("id") Long id, Model model) {
		Warship editWarship = warshipService.find(id);
		model.addAttribute("editWarship", editWarship);
		return "edit_warship";
	}

	@PostMapping("/update/{id}")
    public String modifyBook(@PathVariable("id") Long id,
            @Valid @ModelAttribute("editWarship") Warship editWarship,  
            Errors errors, Model model) {
		if (!warshipService.isInputValid(editWarship, errors)) {
            return "edit_warship";
		} 

        editWarship.setId(id);
		warshipService.update(editWarship);

        return "redirect:/warships";
    }

	@PostMapping("/save")
	public String saveNewWarship(
            @Valid @ModelAttribute("newWarship") Warship newWarship,
            Errors errors) {
		if (!warshipService.isInputValid(newWarship, errors)) {
			return "warships";
		} 

        warshipService.save(newWarship);
        return "redirect:/warships";
    }
	
}

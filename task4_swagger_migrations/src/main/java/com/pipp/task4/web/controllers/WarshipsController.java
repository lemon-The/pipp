package com.pipp.task4.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pipp.task4.pojo.Country;
import com.pipp.task4.pojo.Warship;
import com.pipp.task4.web.services.CountryService;
import com.pipp.task4.web.services.WarshipService;

import jakarta.validation.Valid;

@RestController
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

  //@ModelAttribute(name = "newWarship")
  //public Warship newWarship() {
  //  return new Warship();
  //}

  //@ModelAttribute(name = "countries")
  //public List<Country> allCountries() {
  //  return countryService.findAll();
  //}

  //@ModelAttribute(name = "warships")
  //public List<Warship> allWarships() {
  //  return warshipService.findAll();
  //}

  @GetMapping("/")
  public List<Warship> getAlltWarships() {
    return warshipService.findAll();
  }

  @GetMapping("/{id}")
  public Warship getWarship(@PathVariable("id") Long id) {
    return warshipService.find(id);
  }

  @GetMapping("/delete/{id}")
  public void deleteWarshipByName(@PathVariable("id") Long id) {
    warshipService.delete(id);
  }

  //@GetMapping("/edit/{id}")
  //public String editWarshipById(@PathVariable("id") Long id, Model model) {
  //  Warship editWarship = warshipService.find(id);
  //  model.addAttribute("editWarship", editWarship);
  //  return "edit_warship";
  //}

  @PostMapping("/update/{id}")
  public void modifyBook(@PathVariable("id") Long id,
      @Valid @RequestBody Warship editWarship,
      Errors errors, Model model) {
    if (!warshipService.isInputValid(editWarship, errors)) {
      // TODO
    }

    editWarship.setId(id);
    warshipService.update(editWarship);
  }

  @PostMapping("/save")
  public void saveNewWarship(
      @ModelAttribute("newWarship") Warship newWarship
      ) {

    warshipService.save(newWarship);
  }

}

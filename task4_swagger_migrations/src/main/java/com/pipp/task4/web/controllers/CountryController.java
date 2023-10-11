package com.pipp.task4.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pipp.task4.data.CountryRepository;
import com.pipp.task4.pojo.Country;

@RestController
@RequestMapping("/country")
public class CountryController {
  private CountryRepository countryRepo;

  @Autowired
  public CountryController(CountryRepository countryRepo) {
    this.countryRepo = countryRepo;
  }

  @GetMapping("/")
  public List<Country> getCountries() {
    return countryRepo.findAll();
  }

  @PostMapping("/save")
  public void saveNewBattle(@RequestBody Country newCountry) {
    countryRepo.save(newCountry);
  }

}

package com.pipp.task5.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pipp.task5.data.CountryRepository;
import com.pipp.task5.pojo.Country;

@RestController
@RequestMapping("/country")
//@CrossOrigin(origins = {"http://localhost:8080/country"})
@CrossOrigin
public class CountryController {
  private CountryRepository countryRepo;

  @Autowired
  public CountryController(CountryRepository countryRepo) {
    this.countryRepo = countryRepo;
  }

  @GetMapping("/get")
  //@CrossOrigin
  //@CrossOrigin(origins = {"http://localhost:8080/country/get"})
  public List<Country> getCountries() {
    return countryRepo.findAll();
  }

  @PostMapping("/save")
  //@CrossOrigin(origins = {"http://localhost:8080/country"})
  public void saveNewBattle(@RequestBody Country newCountry) {
    countryRepo.save(newCountry);
  }

}

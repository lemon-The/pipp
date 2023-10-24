package com.pipp.task5;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pipp.task5.data.CountryRepository;
import com.pipp.task5.data.WarshipRepository;
import com.pipp.task5.pojo.Country;
import com.pipp.task5.pojo.Warship;

@Configuration
public class Task5Configuration {

  @Bean
  public CommandLineRunner dataLoader(CountryRepository countryRepo, WarshipRepository warshipRepo) {
    return args -> {
      countriesLoader(countryRepo);
      warshipsLoader(countryRepo, warshipRepo);
    };
  }

  public void countriesLoader(CountryRepository repo) {
    //return args -> {
      repo.save(new Country("USSR", "ALIES"));
      repo.save(new Country("USA", "ALIES"));
      repo.save(new Country("Britain", "ALIES"));
      repo.save(new Country("Germany", "NAZI BLOC"));
      repo.save(new Country("Italy", "NAZI BLOC"));
      repo.save(new Country("Japan", "NAZI BLOC"));
    //};
  }

  public void warshipsLoader(CountryRepository countryRepo, WarshipRepository warshipRepo) {
    //return args -> {
      warshipRepo.save(new Warship(
            "Admiral Graf Spee",
            "CRUISER",
            LocalDate.parse("1936-01-06"),
            LocalDate.parse("1939-12-17"),
            countryRepo.findByName("Germany").get()));
      warshipRepo.save(new Warship(
            "HMS Ajax",
            "CRUISER",
            LocalDate.parse("1934-03-01"),
            LocalDate.parse("1948-02-01"),
            countryRepo.findByName("Britain").get()));
    //};
  }

}

package com.pipp.task5.web.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pipp.task5.data.CountryRepository;
import com.pipp.task5.pojo.Country;

@Service
public class CountryService {
	
	private CountryRepository countryRepo;
	private Logger log;

	@Autowired
	public CountryService(CountryRepository countryDAO) {
		this.countryRepo = countryDAO;
		this.log = LoggerFactory.getLogger(CountryService.class);
	}

	public List<Country> findAll() {
		return countryRepo.findAll();
	}

	public Country find(String name) {
		return countryRepo.findByName(name).get();
	}
	
}

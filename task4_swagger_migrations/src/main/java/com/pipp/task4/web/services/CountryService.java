package com.lemonthe.seabattles.web.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemonthe.seabattles.database.dao.CountryDAO;
import com.lemonthe.seabattles.pojo.Country;

@Service
public class CountryService {
	
	private CountryDAO countryDAO;
	private Logger log;

	@Autowired
	public CountryService(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
		this.log = LoggerFactory.getLogger(CountryService.class);
	}

	public List<Country> findAll() {
		return countryDAO.findAll();
	}

	public Country find(String name) {
		return countryDAO.find(name);
	}
	
}

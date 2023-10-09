package com.lemonthe.seabattles.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.lemonthe.seabattles.database.dao.CountryDAO;
import com.lemonthe.seabattles.pojo.Country;

@Component
public class CountryByNameConverter implements Converter<String, Country> {

	private CountryDAO countryDAO;

	@Autowired
	public CountryByNameConverter(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	@Override
	public Country convert(String name) {
		return countryDAO.find(name);
	}
	
}


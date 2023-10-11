package com.pipp.task4.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pipp.task4.data.CountryRepository;
import com.pipp.task4.pojo.Country;

@Component
public class CountryByNameConverter implements Converter<String, Country> {

	private CountryRepository countryDAO;

	@Autowired
	public CountryByNameConverter(CountryRepository countryDAO) {
		this.countryDAO = countryDAO;
	}

	@Override
	public Country convert(String name) {
		return countryDAO.findByName(name).get();
	}
	
}


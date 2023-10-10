package com.lemonthe.seabattles.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.lemonthe.seabattles.database.dao.WarshipDAO;
import com.lemonthe.seabattles.pojo.Warship;

@Component
public class WarshipByIdConverter implements Converter<String, Warship> {

	private WarshipDAO warshipDAO;

	@Autowired
	public WarshipByIdConverter(WarshipDAO warshipDAO) {
		this.warshipDAO = warshipDAO;
	}

	@Override
	public Warship convert(String id) {
		return warshipDAO.find(Long.parseLong(id));
	}
	
}




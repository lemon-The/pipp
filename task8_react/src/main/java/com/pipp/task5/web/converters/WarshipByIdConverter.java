package com.pipp.task5.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pipp.task5.data.WarshipRepository;
import com.pipp.task5.pojo.Warship;

@Component
public class WarshipByIdConverter implements Converter<String, Warship> {

	private WarshipRepository warshipRepo;

	@Autowired
	public WarshipByIdConverter(WarshipRepository warshipDAO) {
		this.warshipRepo = warshipDAO;
	}

	@Override
	public Warship convert(String id) {
		return warshipRepo.findById(Long.parseLong(id)).get();
	}
	
}




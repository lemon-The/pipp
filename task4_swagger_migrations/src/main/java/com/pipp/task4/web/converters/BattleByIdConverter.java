package com.pipp.task4.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pipp.task4.data.BattleRepository;
import com.pipp.task4.pojo.Battle;

@Component
public class BattleByIdConverter implements Converter<String, Battle> {

	//private BattleService service;
	private BattleRepository battleRepo;

	@Autowired
	public BattleByIdConverter(BattleRepository battleDAO) {
		this.battleRepo = battleDAO;
	}

	@Override
	public Battle convert(String id) {
		return battleRepo.findById(Long.parseLong(id)).get();
	}
	
}



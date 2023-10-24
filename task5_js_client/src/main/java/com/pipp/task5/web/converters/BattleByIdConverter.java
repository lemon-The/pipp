package com.pipp.task5.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pipp.task5.data.BattleRepository;
import com.pipp.task5.pojo.Battle;

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



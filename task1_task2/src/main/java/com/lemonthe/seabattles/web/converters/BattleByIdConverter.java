package com.lemonthe.seabattles.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.lemonthe.seabattles.database.dao.BattleDAO;
import com.lemonthe.seabattles.pojo.Battle;

@Component
public class BattleByIdConverter implements Converter<String, Battle> {

	//private BattleService service;
	private BattleDAO battleDAO;

	@Autowired
	public BattleByIdConverter(BattleDAO battleDAO) {
		this.battleDAO = battleDAO;
	}

	@Override
	public Battle convert(String id) {
		return battleDAO.find(Long.parseLong(id));
	}
	
}



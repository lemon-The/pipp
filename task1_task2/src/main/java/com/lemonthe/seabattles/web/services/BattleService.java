package com.lemonthe.seabattles.web.services;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.lemonthe.seabattles.database.dao.BattleDAO;
import com.lemonthe.seabattles.pojo.Battle;
import com.lemonthe.seabattles.pojo.Warship;

@Service
public class BattleService {

	private BattleDAO battleDAO;
	private Logger log;

	@Autowired
	public BattleService(BattleDAO battleDAO) {
		this.battleDAO = battleDAO;
		this.log = LoggerFactory.getLogger(CountryService.class);
	}

	public List<Battle> findAll() {
		return battleDAO.findAll();
	}

	public void delete(Long id) {
		battleDAO.delete(id);
	}

	public void update(Battle battle) {
		battleDAO.update(battle);
	}

	public void save(Battle battle) {
		battleDAO.save(battle);
	}

	public Battle find(Long id) {
		return battleDAO.find(id);
	}

	//private List<Warship> findBattleMembers(Long id) {
	//	return battleDAO.findBattleMembers(id);
	//}

	public boolean isInputValid(Battle input, Errors errors) {
        if (errors.hasErrors()) {
            log.error(errors.toString());
            return false;
        } else if (input.getDate().isBefore(LocalDate.parse("1939-09-01"))) {
			errors.rejectValue("date", "120",
					"Battle date can not be before the war beginning");
			return false;
		} else if (input.getDate().isAfter(LocalDate.parse("1945-09-02"))) {
			errors.rejectValue("date", "120",
					"Battle date can not be after the war end");
			return false;
		}

		for (Battle battle : battleDAO.findAll()) {
			if (battle.getName().equals(input.getName())
					&& !battle.getId().equals(input.getId())) {
				errors.rejectValue("name", "120",
						"Battle name must be unique");
				return false;
			}
		}

		if (input.getId() != null) {
			for (Warship wship : battleDAO.findBattleMembers(input.getId())) {
				if (wship.getCommissionDate().isAfter(input.getDate())) {
					errors.rejectValue("date", "120",
							"Warship: " + wship.getName()
							+ " was commissioned after new battle date");
					return false;
				} else if (wship.getDecommissionDate() != null
						&& wship.getDecommissionDate().isBefore(input.getDate())) {
					errors.rejectValue("date", "120",
							"Warship: " + wship.getName()
							+ " was decommissioned before new battle date");
					return false;
				}
			}
		}

		return true;
	}

}

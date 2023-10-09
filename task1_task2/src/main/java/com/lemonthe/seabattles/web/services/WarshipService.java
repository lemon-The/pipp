package com.lemonthe.seabattles.web.services;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.lemonthe.seabattles.database.dao.WarshipDAO;
import com.lemonthe.seabattles.pojo.Battle;
import com.lemonthe.seabattles.pojo.Warship;

@Service
public class WarshipService {

	private WarshipDAO warshipDAO;
	private Logger log;

	@Autowired
	public WarshipService(WarshipDAO warshipDAO) {
		this.warshipDAO = warshipDAO;
		this.log = LoggerFactory.getLogger(CountryService.class);
	}

	public List<Warship> findAll() {
		return warshipDAO.findAll();
	}

	public Warship find(Long id) {
		return warshipDAO.find(id);
	}

	//private List<Battle> findBattlesForWarship(Long shipId) {
		//return warshipDAO.findBattlesForWarship(shipId);
	//}

	public void delete(Long id) {
		warshipDAO.delete(id);
	}

	public void update(Warship warship) {
		warshipDAO.update(warship);
	}

	public void save(Warship warship) {
		warshipDAO.save(warship);
	}

	public boolean isInputValid(Warship input, Errors errors) {
        if (errors.hasErrors()) {
            log.error(errors.toString());
            return false;
        } else if (input.getDecommissionDate() != null
				&& input.getCommissionDate().isAfter(input.getDecommissionDate())) {
			errors.rejectValue("commissionDate", "120",
					"Commission date can not be after decommission date");
			errors.rejectValue("decommissionDate", "120",
					"Decommission date can not be before commission date");
			return false;
		} else if (input.getCommissionDate().isAfter(LocalDate.parse("1945-09-02"))) {
			errors.rejectValue("commissionDate", "120",
					"Warship can not be launched after the war end");
			return false;
		} else if (input.getDecommissionDate() != null
				&& input.getDecommissionDate().isBefore(LocalDate.parse("1939-09-01"))) {
			errors.rejectValue("decommissionDate", "120",
					"Warship can not be decommissioned before the war beginning");
			return false;
		}

		for (Warship wship : warshipDAO.findAll()) {
			if (wship.getName().equals(input.getName()) 
					&& !wship.getId().equals(input.getId())) {
				errors.rejectValue("name", "120",
						"Warship name must be unique");
				return false;
			}
		}

		if (input.getId() != null) {
			for (Battle battle : warshipDAO.findBattlesForWarship(input.getId())) {
				if (input.getCommissionDate().isAfter(battle.getDate())) {
					errors.rejectValue("commissionDate", "120",
							"Battle: " + battle.getName()
							+ " was before new ship commission date");
					return false;
				} else if (input.getDecommissionDate() != null
						&& input.getDecommissionDate().isBefore(battle.getDate())) {
					errors.rejectValue("decommissionDate", "120",
							"Battle: " + battle.getName()
							+ " was after new ship decommission date");
					return false;
				}
			}
		}

		return true;
	}

}

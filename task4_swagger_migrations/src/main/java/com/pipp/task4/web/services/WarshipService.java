package com.pipp.task4.web.services;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.pipp.task4.data.WarshipRepository;
import com.pipp.task4.pojo.Battle;
import com.pipp.task4.pojo.Warship;

@Service
public class WarshipService {

	private WarshipRepository warshipRepo;
	private Logger log;

	@Autowired
	public WarshipService(WarshipRepository warshipDAO) {
		this.warshipRepo = warshipDAO;
		this.log = LoggerFactory.getLogger(CountryService.class);
	}

	public List<Warship> findAll() {
		return warshipRepo.findAll();
	}

	public Warship find(Long id) {
		return warshipRepo.findById(id).get();
	}

	//private List<Battle> findBattlesForWarship(Long shipId) {
		//return warshipDAO.findBattlesForWarship(shipId);
	//}

	public void delete(Long id) {
		warshipRepo.deleteById(id);
	}

	public void update(Warship warship) {
		warshipRepo.save(warship);
	}

	public void save(Warship warship) {
		warshipRepo.save(warship);
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

		for (Warship wship : warshipRepo.findAll()) {
			if (wship.getName().equals(input.getName()) 
					&& !wship.getId().equals(input.getId())) {
				errors.rejectValue("name", "120",
						"Warship name must be unique");
				return false;
			}
		}

		if (input.getId() != null) {
			for (Battle battle : warshipRepo.findById(input.getId()).get().getBattles()) {
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

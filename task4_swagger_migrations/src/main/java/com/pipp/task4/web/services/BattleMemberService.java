//package com.pipp.task4.web.services;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.Errors;
//
//import com.pipp.task4.data.BattleMemberRepository;
//import com.pipp.task4.pojo.BattleMember;
//
//@Service
//public class BattleMemberService {
//	
//	private BattleMemberRepository battleMemberRepo;
//	private Logger log;
//
//	@Autowired
//	public BattleMemberService(BattleMemberRepository battleMemberDAO) {
//		this.battleMemberRepo = battleMemberDAO;
//		this.log = LoggerFactory.getLogger(CountryService.class);
//	}
//
//	public List<BattleMember> findAll() {
//		return battleMemberRepo.findAll();
//	}
//
//	public BattleMember find(Long id) {
//		return battleMemberRepo.findById(id).get();
//	}
//
//	//private List<BattleMember> findBattleMembersByBattleName(String name) {
//		//return battleMemberDAO.findBattleMembersByBattleName(name);
//	//}
//
//	public void delete(Long id) {
//		battleMemberRepo.deleteById(id);
//	}
//
//	public void update(BattleMember battleMember) {
//		battleMemberRepo.save(battleMember);
//	}
//	
//	public void save(BattleMember battleMember) {
//		battleMemberRepo.save(battleMember);
//	}
//
//	public boolean isInputValid(BattleMember input, Errors errors) {
//        if (errors.hasErrors()) {
//            log.error(errors.toString());
//            return false;
//        } else if (input.getMember().getCommissionDate().isAfter(input.getBattle().getDate())) {
//			errors.rejectValue("battle", "120",
//					"Battle date can not be before the warship was commissioned");
//			errors.rejectValue("member", "120",
//					"Warship can not be commissioned after the battle date");
//			return false;
//		} else if (input.getMember().getDecommissionDate() != null
//				&& input.getMember().getDecommissionDate().isBefore(input.getBattle().getDate())) {
//			errors.rejectValue("battle", "120",
//					"Battle date can not be after the warship was decommissioned");
//			errors.rejectValue("member", "120",
//					"Warship can not be decommissioned before the battle date");
//			return false;
//		} 
//
//		for (BattleMember bMember : battleMemberRepo.findByBattleName(input.getBattle().getName())) {
//			if (bMember.getMember().equals(input.getMember())
//					&& !bMember.getId().equals(input.getId())) {
//				errors.rejectValue("member", "120",
//						"Warship is already a member of this battle");
//				return false;
//			}
//		}
//
//		return true;
//	}
//}

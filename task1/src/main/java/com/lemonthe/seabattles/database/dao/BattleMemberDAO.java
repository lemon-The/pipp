package com.lemonthe.seabattles.database.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lemonthe.seabattles.database.DataBaseConnectionProvider;
import com.lemonthe.seabattles.database.interfaces.DataBaseEditor;
import com.lemonthe.seabattles.database.interfaces.DataBaseReader;
import com.lemonthe.seabattles.pojo.Battle;
import com.lemonthe.seabattles.pojo.BattleMember;
import com.lemonthe.seabattles.pojo.Country;
import com.lemonthe.seabattles.pojo.Warship;

@Component
public class BattleMemberDAO implements DataBaseReader<BattleMember, Long>,
		DataBaseEditor<BattleMember, Long> {
	
	private DataBaseConnectionProvider connectionProvider;
	private Logger log;

	@Autowired
	public BattleMemberDAO(DataBaseConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
		this.log = LoggerFactory.getLogger(BattleMemberDAO.class);
	}

	@Override
	public void delete(Long id) {
		String query = "DELETE FROM battle_members WHERE id = ?";
		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setLong(1, id);
			pstmt.execute();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(BattleMember battleMember) {
		String query = "UPDATE battle_members SET id = ?"
					+ ", battle_name = ?"
					+ ", warship_name = ?"
					+ ", battle_result = ? "
					+ "WHERE id = ?";
		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setLong(1, battleMember.getId());
			pstmt.setString(2, battleMember.getBattle().getName());
			pstmt.setString(3, battleMember.getMember().getName());
			pstmt.setString(4, battleMember.getResult());
			pstmt.setLong(5, battleMember.getId());
			pstmt.execute();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void save(BattleMember battleMember) {
		String query = "UPDATE OR INSERT INTO battle_members VALUES("
					+ "next value for battle_members_seq, ?, ?, ?)";
		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, battleMember.getBattle().getName());
			pstmt.setString(2, battleMember.getMember().getName());
			pstmt.setString(3, battleMember.getResult());
			pstmt.execute();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BattleMember> findAll() {
		List<BattleMember> battleMembers = new ArrayList<>();
		String query = "SELECT w.id, w.name, w.class, w.commission_date, "
				+ "w.decommission_date, c.name, c.side, "
				+ "b.id, b.battle_name, b.battle_date, "
				+ "bm.id, bm.battle_result "
				+ "FROM battle_members bm, warships w, "
				+ "countries c, battles b "
				+ "WHERE w.country_name = c.name AND "
				+ "bm.battle_name = b.battle_name AND "
				+ "bm.warship_name = w.name";
		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Warship warship = new Warship();
				warship.setId(rs.getLong(1));
				warship.setName(rs.getString(2));
				warship.setShipClass(rs.getString(3));
				warship.setCommissionDate(LocalDate.parse(rs.getString(4)));
				warship.setDecommissionDate(
						rs.getString(5) == null ? null : LocalDate.parse(rs.getString(5)));
				warship.setCountry(
						new Country(rs.getString(6), rs.getString(7)));

				Battle battle = new Battle();
				battle.setId(rs.getLong(8));
				battle.setName(rs.getString(9));
				battle.setDate(LocalDate.parse(rs.getString(10)));

				BattleMember battleMember = new BattleMember();
				battleMember.setId(rs.getLong(11));
				battleMember.setResult(rs.getString(12));
				//battles.add(new Battle(id, name, date));
				battleMember.setMember(warship);
				battleMember.setBattle(battle);

				battleMembers.add(battleMember);
			}
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return battleMembers;
	}

	@Override
	public BattleMember find(Long id) {
		BattleMember battleMember = new BattleMember();
		String query = "SELECT w.id, w.name, w.class, w.commission_date, "
				+ "w.decommission_date, c.name, c.side, "
				+ "b.id, b.battle_name, b.battle_date, "
				+ "bm.id, bm.battle_result "
				+ "FROM battle_members bm, warships w, "
				+ "countries c, battles b "
				+ "WHERE w.country_name = c.name AND "
				+ "bm.battle_name = b.battle_name AND "
				+ "bm.warship_name = w.name and "
				+ "bm.id = ?";
		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Warship warship = new Warship();
				warship.setId(rs.getLong(1));
				warship.setName(rs.getString(2));
				warship.setShipClass(rs.getString(3));
				warship.setCommissionDate(LocalDate.parse(rs.getString(4)));
				//warship.setDecommissionDate(LocalDate.parse(rs.getString(5)));
				warship.setDecommissionDate(
						rs.getString(5) == null ? null : LocalDate.parse(rs.getString(5)));
				warship.setCountry(
						new Country(rs.getString(6), rs.getString(7)));

				Battle battle = new Battle();
				battle.setId(rs.getLong(8));
				battle.setName(rs.getString(9));
				battle.setDate(LocalDate.parse(rs.getString(10)));

				battleMember.setId(rs.getLong(11));
				battleMember.setResult(rs.getString(12));
				//battles.add(new Battle(id, name, date));
				battleMember.setMember(warship);
				battleMember.setBattle(battle);
			}
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return battleMember;
	}

	public List<BattleMember> findBattleMembersByBattleName(String name) {
		List<BattleMember> battleMembers = new ArrayList<>();
		String query = "SELECT w.id, w.name, w.class, w.commission_date, "
				+ "w.decommission_date, c.name, c.side, "
				+ "b.id, b.battle_name, b.battle_date, "
				+ "bm.id, bm.battle_result "
				+ "FROM battle_members bm, warships w, "
				+ "countries c, battles b "
				+ "WHERE w.country_name = c.name AND "
				+ "bm.battle_name = b.battle_name AND "
				+ "bm.warship_name = w.name AND "
				+ "b.battle_name = ?";

		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Warship warship = new Warship();
				warship.setId(rs.getLong(1));
				warship.setName(rs.getString(2));
				warship.setShipClass(rs.getString(3));
				warship.setCommissionDate(LocalDate.parse(rs.getString(4)));
				//warship.setDecommissionDate(LocalDate.parse(rs.getString(5)));
				warship.setDecommissionDate(
						rs.getString(5) == null ? null : LocalDate.parse(rs.getString(5)));
				warship.setCountry(
						new Country(rs.getString(6), rs.getString(7)));

				Battle battle = new Battle();
				battle.setId(rs.getLong(8));
				battle.setName(rs.getString(9));
				battle.setDate(LocalDate.parse(rs.getString(10)));

				BattleMember battleMember = new BattleMember();
				battleMember.setId(rs.getLong(11));
				battleMember.setResult(rs.getString(12));
				battleMember.setMember(warship);
				battleMember.setBattle(battle);

				battleMembers.add(battleMember);
			}
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return battleMembers;
	}

}

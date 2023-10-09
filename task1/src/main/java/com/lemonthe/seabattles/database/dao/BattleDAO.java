package com.lemonthe.seabattles.database.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lemonthe.seabattles.database.DataBaseConnectionProvider;
import com.lemonthe.seabattles.database.interfaces.DataBaseEditor;
import com.lemonthe.seabattles.database.interfaces.DataBaseReader;
import com.lemonthe.seabattles.pojo.Battle;
import com.lemonthe.seabattles.pojo.Country;
import com.lemonthe.seabattles.pojo.Warship;

@Component
public class BattleDAO implements DataBaseReader<Battle, Long>,
		   DataBaseEditor<Battle, Long> {

	private DataBaseConnectionProvider connectionProvider;
	private Logger log;

	@Autowired
	public BattleDAO(DataBaseConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
		this.log = LoggerFactory.getLogger(BattleDAO.class);
	}

	public void delete(Long id) {
		String query = "DELETE FROM battles WHERE id = ?";
		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setLong(1, id);
			pstmt.execute();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public void update(Battle battle) {
		String query = "UPDATE battles SET battle_name = ?"
						+ ", battle_date = ? "
						+ "WHERE id = ?";
		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmt.setString(1, battle.getName());
			pstmt.setString(2, battle.getDate().toString());
			pstmt.setLong(3, battle.getId());

			pstmt .execute();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public void save(Battle battle) {
		String query = "UPDATE OR INSERT INTO battles VALUES("
						+ "next value for battles_seq, ?, ?)";
		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, battle.getName());
			pstmt.setString(2, battle.getDate().toString());

			pstmt.execute();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public List<Battle> findAll() {
		List<Battle> battles = new ArrayList<>();
		String query = "SELECT * FROM battles";

		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			ResultSet rows = pstmt.executeQuery();

			while (rows.next()) {
				Battle battle = new Battle();

				battle.setId(rows.getLong(1));
				battle.setName(rows.getString(2));
				battle.setDate(LocalDate.parse(rows.getString(3)));

				battles.add(battle);
			}
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return battles;
	}

	public Battle find(Long id) {
		Battle battle = new Battle();
		String query = "SELECT * FROM battles WHERE id = ?";

		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				battle.setId(rs.getLong(1));
				battle.setName(rs.getString(2));
				battle.setDate(LocalDate.parse(rs.getString(3)));
			}
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return battle;
	}

	public List<Warship> findBattleMembers(Long id) {
		List<Warship> warships = new ArrayList<>();
		String query = "SELECT w.id, w.name, w.class, w.commission_date, "
					+ "w.decommission_date, c.name, c.side "
					+ "FROM warships w, countries c, battles b, "
					+ "battle_members bm "
					+ "WHERE w.country_name = c.name AND "
					+ "bm.battle_name = b.battle_name AND "
					+ "bm.warship_name = w.name AND "
					+ "b.id = ?";

		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setLong(1, id);

			ResultSet rows = pstmt.executeQuery();

			while (rows.next()) {
				Warship warship = new Warship();

				warship.setId(rows.getLong(1));
				warship.setName(rows.getString(2));
				warship.setShipClass(rows.getString(3));
				warship.setCommissionDate(LocalDate.parse(rows.getString(4)));
				//warship.setDecommissionDate(LocalDate.parse(rows.getString(5)));
				warship.setDecommissionDate(
						rows.getString(5) == null ? null : LocalDate.parse(rows.getString(5)));
				warship.setCountry(
						new Country(rows.getString(6), rows.getString(7)));
				log.info("Warhips: {}", warship.toString());

				warships.add(warship);
			}
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return warships;
	}
	
}

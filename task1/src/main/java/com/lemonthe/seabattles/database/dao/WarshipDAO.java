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
import com.lemonthe.seabattles.pojo.Country;
import com.lemonthe.seabattles.pojo.Warship;

@Component
public class WarshipDAO implements DataBaseReader<Warship, Long>,
		   DataBaseEditor<Warship, Long> {
	private DataBaseConnectionProvider connectionProvider;
	private Logger log;

	@Autowired
	public WarshipDAO(DataBaseConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
		this.log = LoggerFactory.getLogger(WarshipDAO.class);
	}

	public void delete(Long id) {
		String query = "DELETE FROM warships WHERE id = ?";

		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setLong(1, id);
			pstmt.execute();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public void update(Warship warship) {
		String query = "UPDATE warships SET name = ?"
					+ ", country_name = ?"
					+ ", class = ?"
					+ ", commission_date = ?"
					+ ", decommission_date = ? "
					+ "WHERE id = ?";
		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, warship.getName());
			pstmt.setString(2, warship.getCountry().getName());
			pstmt.setString(3, warship.getShipClass());
			pstmt.setString(4, warship.getCommissionDate().toString());
			//pstmt.setString(5, String.valueOf(warship.getDecommissionDate()));
			pstmt.setString(5, warship.getDecommissionDate() == 
					null ? null : warship.getDecommissionDate().toString());
			pstmt.setLong(6, warship.getId());
			pstmt.execute();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}

	}

	public void save(Warship warship) {
		String query = "UPDATE OR INSERT INTO warships VALUES("
					+ "next value for warships_seq, ?, ?, ?, ?, ?)";
		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, warship.getName());
			pstmt.setString(2, warship.getCountry().getName());
			pstmt.setString(3, warship.getShipClass());
			pstmt.setString(4, warship.getCommissionDate().toString());
			pstmt.setString(5, warship.getDecommissionDate() == 
					null ? null : warship.getDecommissionDate().toString());
			pstmt.execute();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public List<Warship> findAll() {
		List<Warship> warships = new ArrayList<>();
		String query = "SELECT w.id, w.name, w.class, w.commission_date, "
					+ "w.decommission_date, c.name, c.side "
					+ "FROM warships w, countries c "
					+ "WHERE w.country_name = c.name";

		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			ResultSet rows = pstmt.executeQuery();

			while (rows.next()) {
				Warship warship = new Warship();

				warship.setId(rows.getLong(1));
				warship.setName(rows.getString(2));
				warship.setShipClass(rows.getString(3));
				warship.setCommissionDate(LocalDate.parse(rows.getString(4)));
				warship.setDecommissionDate(
						rows.getString(5) == null ? null : LocalDate.parse(rows.getString(5)));
				warship.setCountry(
						new Country(rows.getString(6), rows.getString(7)));

				warships.add(warship);
			}
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return warships;
	}

	public Warship find(Long id) {
		Warship warship = new Warship();
		String query = "SELECT w.id, w.name, w.class, w.commission_date, "
					+ "w.decommission_date, c.name, c.side "
					+ "FROM warships w, countries c "
					+ "WHERE w.country_name = c.name "
					+ "AND w.id = ?";

		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setLong(1, id);
			ResultSet rows = pstmt.executeQuery();

			if (rows.next()) {
				warship.setId(rows.getLong(1));
				warship.setName(rows.getString(2));
				warship.setShipClass(rows.getString(3));
				warship.setCommissionDate(LocalDate.parse(rows.getString(4)));
				//warship.setDecommissionDate(LocalDate.parse(rows.getString(5)));
				warship.setDecommissionDate(
						rows.getString(5) == null ? null : LocalDate.parse(rows.getString(5)));
				warship.setCountry(
						new Country(rows.getString(6), rows.getString(7)));
			}
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return warship;
	}

	public List<Battle> findBattlesForWarship(Long shipId) {
		List<Battle> battles = new ArrayList<>();
		String query = "SELECT b.id, b.battle_name, b.battle_date "
					+ "FROM battles b, warships w, battle_members bm "
					+ "WHERE bm.battle_name = b.battle_name AND " 
					+ "bm.warship_name = w.name AND "
					+ "w.id = ?";

		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setLong(1, shipId);
			ResultSet rows = pstmt.executeQuery();

			while (rows.next()) {
				Battle battle = new Battle();

				battle.setId(rows.getLong(1));
				battle.setName(rows.getString(2));
				battle.setDate(LocalDate.parse(rows.getString(3)));
				log.info("Battle: {}", battle.toString());

				battles.add(battle);
			}
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return battles;
	}

}

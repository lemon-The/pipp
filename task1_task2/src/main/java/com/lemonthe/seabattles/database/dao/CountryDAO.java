package com.lemonthe.seabattles.database.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lemonthe.seabattles.database.DataBaseConnectionProvider;
import com.lemonthe.seabattles.database.interfaces.DataBaseReader;
import com.lemonthe.seabattles.pojo.Country;

@Component
public class CountryDAO implements DataBaseReader<Country, String> {

	private DataBaseConnectionProvider connectionProvider;
	private Logger log;

	@Autowired
	public CountryDAO(DataBaseConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
		this.log = LoggerFactory.getLogger(CountryDAO.class);
	}

	public List<Country> findAll() {
		List<Country> countries = new ArrayList<>();
		String query = "SELECT * FROM countries";

		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString(1);
				String side = rs.getString(2);
				countries.add(new Country(name, side));
			}
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return countries;
	}

	public Country find(String name) {
		Country country = new Country();
		String query = "SELECT * FROM countries WHERE name = ?";

		try (Connection con = connectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				country.setName(rs.getString(1));;
				country.setSide(rs.getString(2));;
			}
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return country;
	}

}

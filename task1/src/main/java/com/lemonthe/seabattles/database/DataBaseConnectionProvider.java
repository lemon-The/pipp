package com.lemonthe.seabattles.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lemonthe.seabattles.config.SeaBattlesDataBaseConfig;

@Component
public class DataBaseConnectionProvider {
	private String url;
	private String name;
	private String password;
	private String driver;
	private String encoding;

	@Autowired
	public DataBaseConnectionProvider(SeaBattlesDataBaseConfig dataBaseConfig) {
        url = dataBaseConfig.getUrl();
        name = dataBaseConfig.getUsername();
        password = dataBaseConfig.getPassword();
		driver = dataBaseConfig.getDriver();
		encoding = dataBaseConfig.getEncoding();
	}

	public Connection getConnection()
			throws SQLException, IOException {
		System.setProperty("firebird.drivers", driver);
		Properties props = new Properties();

		props.setProperty("user", name);
		props.setProperty("password", password);
		props.setProperty("encoding", encoding);

		return DriverManager.getConnection(url, props);
	}

}

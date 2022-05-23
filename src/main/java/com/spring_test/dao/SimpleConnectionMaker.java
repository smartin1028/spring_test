package com.spring_test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 독립시킨 DB 연결 기능
 */
public class SimpleConnectionMaker {
	public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/~/spring", "sa", ""
		);
		return c;
	}
}

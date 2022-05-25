package com.spring_test.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductionlDBConnectionMaker implements ConnectionMaker {
	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		return null;
	}
}

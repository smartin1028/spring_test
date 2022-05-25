package com.spring_test.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class LocalDBConnectionMaker implements ConnectionMaker {
	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		return null;
	}
}

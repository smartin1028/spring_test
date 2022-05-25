package com.spring_test.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker{
	int counter = 0;
	private ConnectionMaker realConnectionMaker;

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
		this.realConnectionMaker = realConnectionMaker;
	}

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		counter++;

		if (dataSource == null) {
			return realConnectionMaker.makeConnection();
		}
		return dataSource.getConnection();
	}

	public int getCounter() {
		return counter;
	}
}

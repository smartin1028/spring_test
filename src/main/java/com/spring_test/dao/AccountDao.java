package com.spring_test.dao;

public class AccountDao {
	ConnectionMaker connectionMaker;

	public AccountDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
}

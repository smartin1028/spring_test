package com.spring_test.dao;

public class MessageDao {

	ConnectionMaker connectionMaker;

	public MessageDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
}

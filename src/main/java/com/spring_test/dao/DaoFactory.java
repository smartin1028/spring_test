package com.spring_test.dao;

public class DaoFactory {
	public UserDao userDao(){
		return new UserDao(connectionMaker());
	}

	private DConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}

	public AccountDao accountDao(){
		return new AccountDao(connectionMaker());
	}
	public MessageDao messageDao(){
		return new MessageDao(connectionMaker());
	}



}

package com.spring_test.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

@Configuration	// 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
public class DaoFactory {
	/**
	 * 스프링 빈 팩토리가 사용할 설정정보를 담은 DaoFactory
	 * @return
	 */
	@Bean
	public UserDao userDao(){
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}

	public AccountDao accountDao(){
		return new AccountDao(connectionMaker());
	}
	public MessageDao messageDao(){
		return new MessageDao(connectionMaker());
	}

	@Bean
	public DataSource dataSource(){
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(org.h2.Driver.class);
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/spring");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}


}

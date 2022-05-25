package com.spring_test.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterDaoFactory {
	@Bean		// 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
	public UserDao userDao(){
		return new UserDao(connectionMaker());
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new CountingConnectionMaker(realConnectionMaker());
	}

	private ConnectionMaker realConnectionMaker() {
		return new DConnectionMaker();
//		return new CountingConnectionMaker(new DConnectionMaker());
	}




}

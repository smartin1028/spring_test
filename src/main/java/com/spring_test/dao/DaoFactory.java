package com.spring_test.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration	// 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
public class DaoFactory {
	/**
	 * 스프링 빈 팩토리가 사용할 설정정보를 담은 DaoFactory
	 * @return
	 */
	@Bean		// 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
	public UserDao userDao(){
		return new UserDao(connectionMaker());
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



}

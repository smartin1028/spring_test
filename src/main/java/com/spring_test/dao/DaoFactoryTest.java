package com.spring_test.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 개발용 운영용 connectionMaker test
 */
@Configuration	// 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
public class DaoFactoryTest {
	@Bean		// 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
	public UserDao userDao(){
		return new UserDao(connectionMaker());
	}
//	개발시
//	@Bean
//	public ConnectionMaker connectionMaker() {
//		return new LocalDBConnectionMaker();
//	}

	// 운영시
	@Bean
	public ConnectionMaker connectionMaker() {
		return new ProductionlDBConnectionMaker();
	}

}

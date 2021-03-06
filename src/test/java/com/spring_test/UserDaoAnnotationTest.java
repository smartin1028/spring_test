package com.spring_test;

import com.spring_test.dao.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(locations = "/config/spring/ApplicationContext.xml")
class UserDaoAnnotationTest {

	@Autowired
	ApplicationContext context;

	@Autowired SimpleDriverDataSource dataSource;
	@Autowired DataSource dataSource3;

	@Autowired
	UserDao dao;

	@BeforeEach
	public void beforeEach(){
		System.out.println("dataSource = " + dataSource3);
		System.out.println("dataSource = " + (dataSource == dataSource3));
	}

	@DisplayName("컨텍스트 테스트")
	@Test
	public void contestTest() throws Exception {
		Integer count = dao.count();
		assertThat(count).isNotNull();
	}


}
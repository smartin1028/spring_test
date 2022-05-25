package com.spring_test;

import com.spring_test.dao.*;
import com.spring_test.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(locations = "/config/spring/ApplicationContext.xml")
class UserDaoAnnotationTest {

	@Autowired
	ApplicationContext context;

	@Autowired
	UserDao userDao;

	@DisplayName("컨텍스트 테스트")
	@Test
	public void contestTest() throws Exception {
		Integer count = userDao.count();
		assertThat(count).isNotNull();
	}

}
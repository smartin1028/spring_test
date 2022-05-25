package com.spring_test;

import com.spring_test.dao.*;
import com.spring_test.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

class UserDaoTest01 {

	ConnectionMaker connectionMaker;
	CountingConnectionMaker countingConnectionMaker;

	ApplicationContext context;
	ApplicationContext context1;
	ApplicationContext context2;

	@BeforeEach
	public void BeforeEachData(){
		System.out.println("================================BeforeEachData = " );
		this.context = new AnnotationConfigApplicationContext(CounterDaoFactory.class);
		this.context1 = new GenericXmlApplicationContext("config/spring/applicationContext.xml");


		System.out.println("================================BeforeEachData end = " );
	}


	@Test
	public void userTest() throws Exception {
		UserDao userDao = context.getBean("userDao", UserDao.class);

		User user = new User();
		String createUserId = "test11";

		user.setId(createUserId);
		user.setName("testname");
		user.setPassword("password");

		userDao.add(user);

		System.out.println("user.getId()  = " + user.getId() + " 등록성공 ");
		User user1 = userDao.get(user.getId());
		System.out.println("user1.getName() = " + user1.getName());
		System.out.println("user1.getPassword() = " + user1.getPassword());
		System.out.println("user1.getId() = " + user1.getId() + " 조회 성공");

	}

	@Test
	public void userDao() throws Exception {
		this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
		System.out.println("connectionMaker = " + connectionMaker);
	}
	@DisplayName("make connection counting test")
	@Test
	public void countingConnectionMaker() throws Exception {
		countingConnectionMaker = context.getBean("connectionMaker", CountingConnectionMaker.class);
		UserDao userDao = context.getBean("userDao", UserDao.class);
		fnAddUser(userDao, "test12");

//		countingConnectionMaker.makeConnection();
		int counter = countingConnectionMaker.getCounter();
		assertThat(counter).isEqualTo(1);
		countingConnectionMaker.makeConnection();
		counter = countingConnectionMaker.getCounter();
		assertThat(counter).isEqualTo(2);

	}

	private User fnAddUser(UserDao userDao, String createUserId) throws ClassNotFoundException, SQLException {
		User user = new User();
		user.setId(createUserId);
		user.setName("testname");
		user.setPassword("password");

		userDao.add(user);
		return user;
	}

	@DisplayName("컨텍스트 테스트")
	@Test
	public void applicationContextTest() throws Exception {
		System.out.println("connectionMaker = " + context1);
		UserDao userDao = context1.getBean("userDao", UserDao.class);
		String id = "test14";
		User result = fnAddUser(userDao, id);

		User user1 = userDao.get(result.getId());

		assertThat(result.getId()).isEqualTo(user1.getId());
		assertThat(result.getPassword()).isEqualTo(user1.getPassword());
		assertThat(result.getName()).isEqualTo(user1.getName());

	}
	@DisplayName("컨텍스트 테스트")
	@Test
	public void applicationContextTest02() throws Exception {
		UserDao userDao1 = context.getBean("userDao", UserDao.class);
		UserDao userDao2 = context1.getBean("userDao", UserDao.class);


		assertThat(userDao1).isNotEqualTo(userDao2);

	}


	@DisplayName("컨텍스트 테스트 ClassPathXmlApplicationContext_test")
	@Test
	public void ClassPathXmlApplicationContext_test() throws Exception {
		UserDao userDao2 = context1.getBean("userDao", UserDao.class);
		this.context2 = new ClassPathXmlApplicationContext("daoContext.xml", UserDao.class);
		System.out.println("connectionMaker = " + context2);
		UserDao userDao3 = context2.getBean("userDao", UserDao.class);
		assertThat(userDao2).isNotEqualTo(userDao3);

	}




}
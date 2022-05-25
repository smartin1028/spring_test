package com.spring_test;

import com.spring_test.dao.*;
import com.spring_test.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

class UserDaoTest01 {

	ConnectionMaker connectionMaker;
	CountingConnectionMaker countingConnectionMaker;

	AnnotationConfigApplicationContext context;

	@BeforeEach
	public void BeforeEachData(){
		this.context = new AnnotationConfigApplicationContext(
				CounterDaoFactory.class);
	}


	@Test
	public void userTest() throws Exception {
		UserDao userDao = context.getBean("userDao", UserDao.class);

		User user = new User();
		String createUserId = "test9";

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
		User user = new User();
		String createUserId = "test10";

		user.setId(createUserId);
		user.setName("testname");
		user.setPassword("password");

		userDao.add(user);

//		countingConnectionMaker.makeConnection();
		int counter = countingConnectionMaker.getCounter();
		Assertions.assertThat(counter).isEqualTo(1);
		countingConnectionMaker.makeConnection();
		counter = countingConnectionMaker.getCounter();
		Assertions.assertThat(counter).isEqualTo(2);

	}


}
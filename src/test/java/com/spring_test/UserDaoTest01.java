package com.spring_test;

import com.spring_test.dao.ConnectionMaker;
import com.spring_test.dao.DaoFactory;
import com.spring_test.dao.UserDao;
import com.spring_test.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

class UserDaoTest01 {

	ConnectionMaker connectionMaker;

	AnnotationConfigApplicationContext context;

	@BeforeEach
	public void BeforeEachData(){
		this.context = new AnnotationConfigApplicationContext(DaoFactory.class);
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



}
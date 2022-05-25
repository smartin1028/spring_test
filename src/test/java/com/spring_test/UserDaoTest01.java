package com.spring_test;

import com.spring_test.dao.*;
import com.spring_test.domain.User;
import org.junit.jupiter.api.AfterEach;
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

	ApplicationContext contextByCounterDaoFactoryJava;
	ApplicationContext contextByGenericXml;
	ApplicationContext contextByClassPathXml;
	ApplicationContext contextByDaoFactoryJava;

	@BeforeEach
	public void BeforeEachData(){
		System.out.println("================================BeforeEachData = " );
		this.contextByCounterDaoFactoryJava = new AnnotationConfigApplicationContext(CounterDaoFactory.class);
		this.contextByDaoFactoryJava = new AnnotationConfigApplicationContext(DaoFactory.class);
		this.contextByGenericXml = new GenericXmlApplicationContext("config/spring/applicationContext.xml");
		System.out.println("================================BeforeEachData end = " );
	}
	@AfterEach
	public void AfterEach() throws SQLException {
		UserDao userDao = contextByGenericXml.getBean("userDao", UserDao.class);
		userDao.deleteAll();
	}

	@Test
	public void userTest() throws Exception {
		UserDao userDao = contextByCounterDaoFactoryJava.getBean("userDao", UserDao.class);

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
		this.connectionMaker = contextByCounterDaoFactoryJava.getBean("connectionMaker", ConnectionMaker.class);
		System.out.println("connectionMaker = " + connectionMaker);
	}
//	@DisplayName("make connection counting test")
//	@Test
//	public void countingConnectionMaker() throws Exception {
//		countingConnectionMaker = contextByCounterDaoFactoryJava.getBean("connectionMaker", CountingConnectionMaker.class);
//		UserDao userDao = contextByCounterDaoFactoryJava.getBean("userDao", UserDao.class);
//		fnAddUser(userDao, "test12");
//
////		countingConnectionMaker.makeConnection();
//		int counter = countingConnectionMaker.getCounter();
//		assertThat(counter).isEqualTo(1);
//		countingConnectionMaker.makeConnection();
//		counter = countingConnectionMaker.getCounter();
//		assertThat(counter).isEqualTo(2);
//
//	}

	private User fnAddUser(UserDao userDao, String createUserId) throws ClassNotFoundException, SQLException {
		User user = new User();
		user.setId(createUserId);
		user.setName("testname");
		user.setPassword("password");

		userDao.add(user);
		return user;
	}

	@DisplayName("xml 컨텍스트 user add")
	@Test
	public void applicationContextTest() throws Exception {
		System.out.println("connectionMaker = " + contextByGenericXml);
		UserDao userDao = contextByGenericXml.getBean("userDao", UserDao.class);
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
		UserDao userDao1 = contextByCounterDaoFactoryJava.getBean("userDao", UserDao.class);
		UserDao userDao2 = contextByGenericXml.getBean("userDao", UserDao.class);


		assertThat(userDao1).isNotEqualTo(userDao2);

	}

//
//	@DisplayName("컨텍스트 테스트 ClassPathXmlApplicationContext_test")
//	@Test
//	public void ClassPathXmlApplicationContext_test() throws Exception {
//		UserDao userDao2 = contextByGenericXml.getBean("userDao", UserDao.class);
//		this.contextByClassPathXml = new ClassPathXmlApplicationContext("daoContext.xml", UserDao.class);
//		System.out.println("connectionMaker = " + contextByClassPathXml);
//		UserDao userDao3 = contextByClassPathXml.getBean("userDao", UserDao.class);
//		assertThat(userDao2).isNotEqualTo(userDao3);
//	}


	@DisplayName("datasource 인터페이스 추가 - java")
	@Test
	public void inteface_datasource_init_java() throws Exception {
		UserDao userDao = contextByDaoFactoryJava.getBean("userDao", UserDao.class);

		String id = "test15";
		User result = fnAddUser(userDao, id);

		User user1 = userDao.get(result.getId());

		assertThat(result.getId()).isEqualTo(user1.getId());
		assertThat(result.getPassword()).isEqualTo(user1.getPassword());
		assertThat(result.getName()).isEqualTo(user1.getName());
	}

	@DisplayName("datasource 인터페이스 추가 - xml")
	@Test
	public void inteface_datasource_init_xml() throws Exception {
		UserDao userDao = contextByDaoFactoryJava.getBean("userDao", UserDao.class);

		String id = "test16";
		User result = fnAddUser(userDao, id);

		User user1 = userDao.get(result.getId());

		assertThat(result.getId()).isEqualTo(user1.getId());
		assertThat(result.getPassword()).isEqualTo(user1.getPassword());
		assertThat(result.getName()).isEqualTo(user1.getName());
	}
	@DisplayName("user 생성자 추가 user total count check")
	@Test
	public void user_add_and_totalCount() throws Exception {
		UserDao userDao = contextByDaoFactoryJava.getBean("userDao", UserDao.class);
		int beforeCnt = userDao.count();
		String id = "testid";

		User user = new User(id, "testname", "password");
		userDao.add(user);

		assertThat(userDao.count()).isEqualTo(beforeCnt+1);
		System.out.println("beforeCnt = " + (beforeCnt+1));

		User user1 = userDao.get(user.getId());

		assertThat(user.getId()).isEqualTo(user1.getId());
		assertThat(user.getPassword()).isEqualTo(user1.getPassword());
		assertThat(user.getName()).isEqualTo(user1.getName());
		userDao.deleteAll();
		assertThat(userDao.count()).isEqualTo(0);
	}

}
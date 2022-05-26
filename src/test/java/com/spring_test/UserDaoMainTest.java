package com.spring_test;

import com.spring_test.dao.UserDao;
import com.spring_test.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoMainTest {

	ApplicationContext context;

	@BeforeEach
	public void BeforeEachData(){
		this.context = new GenericXmlApplicationContext("config/spring/applicationContext.xml");
	}
	@AfterEach
	public void AfterEach() throws SQLException {
		UserDao userDao = context.getBean("userDao", UserDao.class);
		userDao.deleteAll();
	}

	@DisplayName("사용자 등록 테스트")
	@Test
	public void user_add_V1() throws Exception {

		UserDao userDao = context.getBean("userDao", UserDao.class);
		int beforeCnt = userDao.count();
		String id = "testid";

		User user = new User(id, "testname", "password");
		userDao.add(user);
		assertThat(userDao.count()).isEqualTo(beforeCnt+1);
		User user1 = userDao.get(user.getId());
		assertThat(user.getId()).isEqualTo(user1.getId());
		assertThat(user.getPassword()).isEqualTo(user1.getPassword());
		assertThat(user.getName()).isEqualTo(user1.getName());
		userDao.deleteAll();
		assertThat(userDao.count()).isEqualTo(0);
	}

	@DisplayName("사용자 삭제 테스트")
	@Test
	public void user_del_V1() throws Exception {
		UserDao userDao = context.getBean("userDao", UserDao.class);
		int beforeCnt = userDao.count();
		String id = "testid";
		User user = new User(id, "testname", "password");
		userDao.add(user);

		assertThat(userDao.count()).isEqualTo(beforeCnt+1);

		User user1 = userDao.get(user.getId());

		assertThat(user.getId()).isEqualTo(user1.getId());
		assertThat(user.getPassword()).isEqualTo(user1.getPassword());
		assertThat(user.getName()).isEqualTo(user1.getName());
		userDao.deleteAll();
		assertThat(userDao.count()).isEqualTo(0);
	}

}
package com.spring_test;

import com.spring_test.dao.UserDao;
import com.spring_test.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext
class UserDaoDirtiesContextTest {

	UserDao dao;

	@BeforeEach
	public void beforeEach(){
		DataSource dataSource = new SingleConnectionDataSource(
				"jdbc:h2:tcp://localhost/~/spring", "sa" , "", true
		);
		dao = new UserDao();
		dao.setDataSource(dataSource);
	}

	@DisplayName("컨텍스트 테스트")
	@Test
	public void contestTest() throws Exception {
		Integer count = dao.count();
		assertThat(count).isNotNull();
		dao.add(new User("id", "name" , "pass"));
		assertThat(dao.count()).isEqualTo(count + 1);

	}


}
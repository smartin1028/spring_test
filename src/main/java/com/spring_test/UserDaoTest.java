package com.spring_test;

import com.spring_test.dao.ConnectionMaker;
import com.spring_test.dao.DConnectionMaker;
import com.spring_test.dao.DaoFactory;
import com.spring_test.dao.UserDao;
import com.spring_test.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {


		UserDao userDao = new DaoFactory().userDao();

		User user = new User();
		user.setId("test5");
		user.setName("testname");
		user.setPassword("password");
		
		userDao.add(user);

		System.out.println("user.getId()  = " + user.getId() + " 등록성공 ");
		User user1 = userDao.get(user.getId());
		System.out.println("user1.getName() = " + user1.getName());
		System.out.println("user1.getPassword() = " + user1.getPassword());
		System.out.println("user1.getId() = " + user1.getId() + " 조회 성공");
	}
}


package com.spring_test.domain;

import com.spring_test.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoDeleteAll extends UserDao {
//	@Override
	protected PreparedStatement makeStatement(Connection c) throws SQLException {
		return c.prepareStatement(
				"delete from tb_user where 1=1"
		);
	}
}

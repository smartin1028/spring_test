package com.spring_test.dao;

import com.spring_test.domain.User;

import java.sql.*;

public class UserDao {
	private SimpleConnectionMaker simpleConnectionMaker;
	private ConnectionMaker connectionMaker;

	public UserDao() {
		this.connectionMaker = new DConnectionMaker();
	}

	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();

		PreparedStatement ps = c.prepareStatement(
				"insert into tb_user(id, name,password) values (?,?,?)"
		);
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		ps.executeUpdate();
		ps.close();
		c.close();

	}
	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();

		PreparedStatement ps = c.prepareStatement(
				"select * from tb_user where id = ?"
		);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();

		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));

		rs.close();
		ps.close();
		c.close();
		return user;
	}

	public void setConnectionMaker(DConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	/**
	 * 상속을 통한 확장 방법 제공
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
//	public abstract Connection simpleConnectionMaker() throws ClassNotFoundException, SQLException;
//	{
//		Class.forName("org.h2.Driver");
//		Connection c = DriverManager.getConnection(
//				"jdbc:h2:tcp://localhost/~/spring", "sa", ""
//		);
//		return c;
//	}
}

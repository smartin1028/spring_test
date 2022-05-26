package com.spring_test.dao;

import com.spring_test.domain.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
	private DataSource dataSource;

	private SimpleConnectionMaker simpleConnectionMaker;
	private ConnectionMaker connectionMaker;

	public UserDao() {
		this.connectionMaker = new DConnectionMaker();
	}

	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		StatementStrategy strategy = new AddStatement(user);
		jdbcContextWithStatementStrategy(strategy);

	}
	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = dataSource.getConnection();

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

	public int deleteAll() throws SQLException {
		StatementStrategy strategy = new DeleteAllStatement();
		int delCnt = jdbcContextWithStatementStrategy(strategy);
		return delCnt;
	}

	private int jdbcContextWithStatementStrategy(StatementStrategy strategy) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		int updateCnt = -1;
		try {

			c = dataSource.getConnection();
			ps = strategy.makePreparedStatement(c);
			updateCnt = ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}finally {
			if (ps != null) try {ps.close();} catch (SQLException e) { }
			if (c != null) try {c.close();} catch (SQLException e) { }
		}
		return updateCnt;
	}
//
//	abstract protected PreparedStatement makeStatement(Connection c) throws SQLException;
////	{
////		return c.prepareStatement(
////				"delete from tb_user where 1=1"
////		);
////	}

	public int count() throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cnt = -1;
		try {
			c = dataSource.getConnection();
			ps = c.prepareStatement(
					"select count(*) as cnt from tb_user"
			);
			rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		} catch (SQLException e) {
			throw e;
		}finally {
			if (rs != null) try {rs.close();} catch (SQLException e) { }
			if (ps != null) try {ps.close();} catch (SQLException e) { }
			if (c != null) try {c.close();} catch (SQLException e) { }
		}
		return cnt;
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


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}

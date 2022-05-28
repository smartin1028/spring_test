package com.spring_test.dao;

import com.spring_test.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoOLD {
	private DataSource dataSource;

	private JdbcContext jdbcContext;

	private SimpleConnectionMaker simpleConnectionMaker;
	private ConnectionMaker connectionMaker;

	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	public UserDaoOLD() {
		this.connectionMaker = new DConnectionMaker();
	}

	public UserDaoOLD(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

//	public void add(User user) throws ClassNotFoundException, SQLException {
//		StatementStrategy strategy = new AddStatement(user);
//		jdbcContextWithStatementStrategy(strategy);
//	}

	/**
	 * 내부 클래스에서 외부의 변수를 사용할 때는 외부 변수는 반드시 final로 선언해줘야 한다.
	 * user파라미터는 메소드 내부에서 변경될 일이 없으므로 final로 선언해도 무방하다.
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
//	public void add(final User user) throws ClassNotFoundException, SQLException {
//		/**
//		 * 메소드 내의 로컬 클래스로 이전한 AddStatement
//		 * 내부 로컬 클래스에서는 user 변수를 사용할 수 있으므로 넘겨주지 않아도 된다.
//		 */
//		class AddStatement implements StatementStrategy{
////			User user;
////
////			public AddStatement(User user) {
////				this.user = user;
////			}
//
//			@Override
//			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//				PreparedStatement ps = c.prepareStatement(
//						"insert into tb_user(id, name,password) values (?,?,?)"
//				);
//				ps.setString(1, user.getId());
//				ps.setString(2, user.getName());
//				ps.setString(3, user.getPassword());
//				return ps;
//			}
//		}
//
//		StatementStrategy strategy = new AddStatement();
//		jdbcContextWithStatementStrategy(strategy);
//	}

	/**
	 * 익멸 내부 클래스로 전환
	 *
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void add(final User user) throws ClassNotFoundException, SQLException {
		jdbcContext.workWithStatementStrategy(c -> {
			PreparedStatement ps = c.prepareStatement(
					"insert into tb_user(id, name,password) values (?,?,?)"
			);
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			return ps;
		});
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
		return jdbcContext.excuteSql("delete from tb_user where 1=1");
	}

	private int excuteSql(String sql) throws SQLException {
		return jdbcContext.workWithStatementStrategy(c -> c.prepareStatement(sql));
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
		} finally {
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
			}
			if (c != null) try {
				c.close();
			} catch (SQLException e) {
			}
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
		} finally {
			if (rs != null) try {
				rs.close();
			} catch (SQLException e) {
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
			}
			if (c != null) try {
				c.close();
			} catch (SQLException e) {
			}
		}
		return cnt;
	}

	public void setConnectionMaker(DConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	/**
	 * 상속을 통한 확장 방법 제공
	 *
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

	/**
	 * dataSource가 DI될 때 JdbcContext 생성
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		// jdbcContext를 주입받지 않고 직접 생성

		jdbcContext = new JdbcContext();
		jdbcContext.setDataSource(dataSource);
	}
}

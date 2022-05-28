package com.spring_test.dao;

import com.spring_test.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
	private DataSource dataSource;


	private JdbcTemplate jdbcTemplate;

	private SimpleConnectionMaker simpleConnectionMaker;
	private ConnectionMaker connectionMaker;

	public UserDao() {
		this.connectionMaker = new DConnectionMaker();
	}

	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	/**
	 * 익멸 내부 클래스로 전환
	 *
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void add(final User user) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update("insert into tb_user(id, name,password) values (?,?,?)"
				, user.getId()
				, user.getName()
				, user.getPassword()
		);

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
		return jdbcTemplate.update("delete from tb_user where 1=1");
	}


	public int count() throws SQLException {
		return Integer.valueOf(jdbcTemplate.queryForMap("select count(*) as cnt from tb_user").get("cnt").toString());
	}

	public int countV2() throws SQLException {
		return jdbcTemplate.query(
				con -> con.prepareStatement("select count(*) as cnt from tb_user"),
				rs -> {
					rs.next();
					return rs.getInt("cnt");
				}
		);
	}
	public int countV1() throws SQLException {
		return jdbcTemplate.query(new PreparedStatementCreator() {
									  @Override
									  public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
										  return con.prepareStatement("select count(*) as cnt from tb_user");
									  }
								  }, new ResultSetExtractor<Integer>() {

									  @Override
									  public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
										  rs.next();
										  return rs.getInt("cnt");
									  }
								  }
		);
	}
	public void setConnectionMaker(DConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	/**
	 * dataSource가 DI될 때 JdbcContext 생성
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
}

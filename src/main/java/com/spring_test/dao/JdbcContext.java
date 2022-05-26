package com.spring_test.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		return statementStrategy(stmt);
	}

	private int statementStrategy(StatementStrategy stmt) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		int updateCnt = -1;
		try {

			c = dataSource.getConnection();
			ps = stmt.makePreparedStatement(c);
			updateCnt = ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) try {ps.close();} catch (SQLException e) {}
			if (c != null) try {c.close();} catch (SQLException e) {}
		}
		return updateCnt;
	}

	public int excuteSql(String sql) throws SQLException {
		return workWithStatementStrategy(c -> c.prepareStatement(sql));
	}
}

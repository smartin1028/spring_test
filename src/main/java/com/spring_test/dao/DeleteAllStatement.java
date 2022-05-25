package com.spring_test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy{
	@Override
	public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
		return c.prepareStatement(
				"delete from tb_user where 1=1"
		);
	}
}

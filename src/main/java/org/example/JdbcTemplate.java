package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
    public void executeUpdate(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionManager.getConnection();

//            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatementSetter.setter(preparedStatement);

//            preparedStatement.setString(1, user.getUserId());
//            preparedStatement.setString(2, user.getPassword());
//            preparedStatement.setString(3, user.getName());
//            preparedStatement.setString(4, user.getEmail());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    public Object executeQuery(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionManager.getConnection();

//            String sql = "SELECT userId, password, name, email FROM USERS WHERE userId=?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatementSetter.setter(preparedStatement);

//            preparedStatement.setString(1, userId);

            resultSet = preparedStatement.executeQuery();

            Object obj = null;
            if (resultSet.next()) {
                return rowMapper.map(resultSet);
//                user = new User(
//                        resultSet.getString("userId"),
//                        resultSet.getString("password"),
//                        resultSet.getString("name"),
//                        resultSet.getString("email")
//                );
            }

            return obj;
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }
}

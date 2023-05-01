package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final int MAX_POOL_SIZE = 40;
    private static final DataSource dataSource;

    static {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setDriverClassName(DB_DRIVER);
        hikariDataSource.setJdbcUrl(DB_URL);
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);

        dataSource = hikariDataSource;
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

//        String url = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
//        String id = "sa";
//        String pw = "";
//
//        try {
//            Class.forName("org.h2.Driver");
//            return DriverManager.getConnection(url, id, pw);
//        } catch (Exception e) {
//            return null;
//        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

//    public static DataSource getDataSource() {
//        HikariDataSource hikariDataSource = new HikariDataSource();
//
//        hikariDataSource.setDriverClassName(DB_DRIVER);
//        hikariDataSource.setJdbcUrl(DB_URL);
//        hikariDataSource.setUsername("sa");
//        hikariDataSource.setPassword("");
//        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
//        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);
//
//        return hikariDataSource;
//    }
}

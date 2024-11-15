package com.studentmanagement.student_lesson_servlet.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnectionProvider {
  private static SQLConnectionProvider instance;

  private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student_lesson_db";
  private static final String JDBC_USERNAME = "root";
  private static final String JDBC_PASSWORD = "root";

  private Connection connection;

  private SQLConnectionProvider() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static SQLConnectionProvider getInstance() {
    if (instance == null) {
      instance = new SQLConnectionProvider();
    }
    return instance;
  }

  public Connection getConnection() {
    try {
      if (connection == null || connection.isClosed()) {
        connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }
}

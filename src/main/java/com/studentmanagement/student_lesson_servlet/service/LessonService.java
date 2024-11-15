package com.studentmanagement.student_lesson_servlet.service;

import com.studentmanagement.student_lesson_servlet.database.SQLConnectionProvider;
import com.studentmanagement.student_lesson_servlet.model.Lesson;
import com.studentmanagement.student_lesson_servlet.util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class LessonService {

  private final Connection connection = SQLConnectionProvider.getInstance().getConnection();

  public void add(Lesson lesson) {
    String query = """
        INSERT INTO lesson (name, duration, lecturer_name, price) VALUES ('%s', '%s', '%s', '%f')
        """.formatted(
        lesson.getName(),
        DateUtil.fromDateToSqlString(lesson.getDuration()),
        lesson.getLecturerName(),
        lesson.getPrice());
    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
      ResultSet generatedKeys = statement.getGeneratedKeys();

      if (generatedKeys.next()) {
        lesson.setId(generatedKeys.getInt(1));
      }

    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public List<Lesson> getAllLessons() {
    String query = "SELECT * FROM lesson";
    List<Lesson> lessons = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        Lesson lesson = new Lesson(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            DateUtil.fromSqlStringToDate(resultSet.getString("duration")),
            resultSet.getString("lecturer_name"),
            resultSet.getDouble("price")
        );
        lessons.add(lesson);
      }
    } catch (SQLException | ParseException e) {
      throw new RuntimeException(e.getMessage());
    }
    return lessons;
  }

  public Lesson getLessonById(int id) {
    String query = "SELECT * FROM lesson WHERE id = " + id;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(query);
      if (resultSet.next()) {
        Lesson lesson = new Lesson(
            resultSet.getString("name"),
            DateUtil.fromSqlStringToDate(resultSet.getString("duration")),
            resultSet.getString("lecturer_name"),
            resultSet.getDouble("price")
        );
        lesson.setId(id);
        return lesson;
      }
    } catch (SQLException | ParseException e) {
      throw new RuntimeException(e.getMessage());
    }
    return null;
  }

  public void deleteLessonById(int id) {
    String sql = "DELETE FROM lesson WHERE id = " + id;
    try {
      connection.createStatement().executeUpdate(sql);
    } catch (SQLException e) {
      throw new RuntimeException("Failed to delete lesson with id " + id);
    }
  }

  public void updateLessonById(Lesson lesson) {
    String query = """
        UPDATE lesson SET name = '%s', duration = '%s', lecturer_name = '%s', price = '%f'
        WHERE id = %d
        """.formatted(
        lesson.getName(),
        DateUtil.fromDateToSqlString(lesson.getDuration()),
        lesson.getLecturerName(),
        lesson.getPrice(),
        lesson.getId());
    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}

package com.studentmanagement.student_lesson_servlet.service;

import com.studentmanagement.student_lesson_servlet.database.SQLConnectionProvider;
import com.studentmanagement.student_lesson_servlet.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
  private final Connection connection = SQLConnectionProvider.getInstance().getConnection();
  private final LessonService lessonService = new LessonService();

  public void add(Student student) {
    String query = """
        INSERT INTO student (name, surname, email, age, lesson_id) VALUES ('%s', '%s', '%s', '%d', '%d')
        """.formatted(
        student.getName(),
        student.getSurname(),
        student.getEmail(),
        student.getAge(),
        student.getLesson().getId()
    );
    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
      ResultSet generatedKeys = statement.getGeneratedKeys();

      if (generatedKeys.next()) {
        student.setId(generatedKeys.getInt(1));
      }

    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public List<Student> getAllStudents() {
    String query = "SELECT * FROM student";
    List<Student> students = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        Student student = new Student(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("surname"),
            resultSet.getString("email"),
            resultSet.getInt("age"),
            this.lessonService.getLessonById(resultSet.getInt("lesson_id"))
        );
        students.add(student);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    }
    return students;
  }

  public Student getStudentById(int id) {
    String query = "SELECT * FROM student WHERE id = " + id;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(query);
      if (resultSet.next()) {
        Student student = new Student(
            resultSet.getString("name"),
            resultSet.getString("surname"),
            resultSet.getString("email"),
            resultSet.getInt("age"),
            this.lessonService.getLessonById(resultSet.getInt("lesson_id"))
        );
        student.setId(id);
        return student;
      }
    } catch (SQLException e) {
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

  public void updateStudentById(Student student) {
    String query = """
        UPDATE lesson SET name = '%s', surname = '%s', email = '%s', age = '%d', lesson_id = '%d'
        WHERE id = %d
        """.formatted(
        student.getName(),
        student.getSurname(),
        student.getEmail(),
        student.getAge(),
        student.getLesson().getId(),
        student.getLesson().getId());
    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}

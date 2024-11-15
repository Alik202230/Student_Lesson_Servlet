package com.studentmanagement.student_lesson_servlet.servlet;

import com.studentmanagement.student_lesson_servlet.model.Student;
import com.studentmanagement.student_lesson_servlet.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

  private final StudentService studentService = new StudentService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Student> students = studentService.getAllStudents();
    req.setAttribute("students", students);
    req.getRequestDispatcher("/students.jsp").forward(req, resp);
  }
}

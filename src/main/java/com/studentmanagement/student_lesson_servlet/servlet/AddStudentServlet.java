package com.studentmanagement.student_lesson_servlet.servlet;

import com.studentmanagement.student_lesson_servlet.model.Lesson;
import com.studentmanagement.student_lesson_servlet.model.Student;
import com.studentmanagement.student_lesson_servlet.service.LessonService;
import com.studentmanagement.student_lesson_servlet.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {

  private final LessonService lessonService = new LessonService();
  private final StudentService studentService = new StudentService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Lesson> allLessons = lessonService.getAllLessons();
    req.setAttribute("lessons", allLessons);
    req.getRequestDispatcher("/addStudent.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("name");
    String surname = req.getParameter("surname");
    String email = req.getParameter("email");
    int age = Integer.parseInt(req.getParameter("age"));
    int lessonId = Integer.parseInt(req.getParameter("lesson_id"));
    Lesson lesson = this.lessonService.getLessonById(lessonId);

    Student student = new Student(name, surname, email, age, lesson);
    this.studentService.add(student);

    resp.sendRedirect("/students");
  }
}

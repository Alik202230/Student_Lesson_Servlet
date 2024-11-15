package com.studentmanagement.student_lesson_servlet.servlet;

import com.studentmanagement.student_lesson_servlet.model.Lesson;
import com.studentmanagement.student_lesson_servlet.service.LessonService;
import com.studentmanagement.student_lesson_servlet.util.DateUtil;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-lesson")
public class AddLessonServlet extends HttpServlet {

  private final LessonService lessonService = new LessonService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/addLesson.jsp").forward(req, resp);
  }


  @Override
  @SneakyThrows
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    String name = req.getParameter("name");
    String duration = req.getParameter("duration");
    String lecturerName = req.getParameter("lecturer_name");
    double price = Double.parseDouble(req.getParameter("price"));

    Lesson lesson = new Lesson(name, DateUtil.fromWebStringToDate(duration), lecturerName, price);

    this.lessonService.add(lesson);
    resp.sendRedirect("/lessons");
  }
}

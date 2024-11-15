package com.studentmanagement.student_lesson_servlet.servlet;

import com.studentmanagement.student_lesson_servlet.service.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-lesson")
public class DeleteLessonServlet extends HttpServlet {

  private final LessonService lessonService = new LessonService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id  = Integer.parseInt(req.getParameter("id"));
    this.lessonService.deleteLessonById(id);
    resp.sendRedirect("/lessons");
  }
}

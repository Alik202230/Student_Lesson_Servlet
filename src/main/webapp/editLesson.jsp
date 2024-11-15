<%@ page import="com.studentmanagement.student_lesson_servlet.model.Lesson" %>
<%@ page import="com.studentmanagement.student_lesson_servlet.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: alik
  Date: 11/15/24
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit Lesson Page</title>
</head>
<body>
<% Lesson lesson = (Lesson) request.getAttribute("lesson");%>
<h1>Edit Lesson</h1>
<a href="/lessons">Lessons</a><br>
<form action="/edit-lesson" method="post">
  <input type="hidden" name="id" value="<%=lesson.getId()%>"><br>
  Name: <input type="text" name="name" value="<%=lesson.getName()%>"><br>
  Duration: <input type="date" name="duration" value="<%=DateUtil.fromDateToWebString(lesson.getDuration())%>"><br>
  Lecturer Name: <input type="text" name="lecturer_name" value="<%=lesson.getLecturerName()%>"><br>
  Price: <input type="number" name="price" value="<%=lesson.getPrice()%>"><br>
  <input type="submit" value="UPDATE">
</form>
</body>
</html>

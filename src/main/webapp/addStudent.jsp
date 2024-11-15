<%@ page import="java.util.List" %>
<%@ page import="com.studentmanagement.student_lesson_servlet.model.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: alik
  Date: 11/16/24
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add Student Page</title>
</head>
<body>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>
<h1>Add Student</h1>
<a href="/students">Students</a><br>
<form action="/add-student" method="post">
  Name: <input type="text" name="name"><br>
  Surname: <input type="text" name="surname"><br>
  Email: <input type="text" name="email"><br>
  Age: <input type="number" name="age"><br>
  Lesson: <select name="lesson_id">
  <%for (Lesson lesson : lessons) { %>
  <option value="<%=lesson.getId()%>">
    <%=lesson.getName() + " " + lesson.getPrice()%>
  </option>
  <%}%>
</select><br>
  <input type="submit" value="ADD">
</form>
</body>
</html>

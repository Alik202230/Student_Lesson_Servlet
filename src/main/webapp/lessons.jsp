<%@ page import="java.util.List" %>
<%@ page import="com.studentmanagement.student_lesson_servlet.model.Lesson" %>
<%@ page import="com.studentmanagement.student_lesson_servlet.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: alik
  Date: 11/15/24
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Lessons Page</title>
</head>
<body>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>
<h1>Lessons:</h1><br>
<a href="/add-lesson">Add Lesson</a>
<table border="1">
  <tr>
    <th>id</th>
    <th>name</th>
    <th>duration</th>
    <th>lecturer name</th>
    <th>price</th>
    <th>actions</th>
  </tr>

  <%for (Lesson lesson : lessons) {%>
  <tr>
    <td><%=lesson.getId()%></td>
    <td><%=lesson.getName()%></td>
    <td><%=DateUtil.fromDateToString(lesson.getDuration())%></td>
    <td><%=lesson.getLecturerName()%></td>
    <td><%=lesson.getPrice()%></td>
    <td>
      <a href="/delete-lesson?id=<%=lesson.getId()%>">Delete</a> /
      <a href="/edit-lesson?id=<%=lesson.getId()%>">Edit</a>
    </td>
  </tr>
  <%}%>
</table>
</body>
</html>

<%@ page import="java.util.List" %>
<%@ page import="com.studentmanagement.student_lesson_servlet.model.Student" %><%--
  Created by IntelliJ IDEA.
  User: alik
  Date: 11/16/24
  Time: 12:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Students Page</title>
</head>
<body>
<% List<Student> students = (List<Student>) request.getAttribute("students");%>
<h1>Students:</h1><br>
<a href="/add-student">Add Students</a>
<table border="1">
  <tr>
    <th>id</th>
    <th>name</th>
    <th>surname</th>
    <th>email</th>
    <th>age</th>
    <th>lesson</th>
    <th>action</th>
  </tr>

  <%for (Student student : students) {%>
  <tr>
    <td><%=student.getId()%></td>
    <td><%=student.getName()%></td>
    <td><%=student.getSurname()%></td>
    <td><%=student.getEmail()%></td>
    <td><%=student.getAge()%></td>
    <td><%=student.getLesson().getName()%></td>
    <td>
      <a href="/delete-student?id=<%=student.getId()%>">Delete</a> /
      <a href="/edit-student?id=<%=student.getId()%>">Edit</a>
    </td>
  </tr>
  <%}%>
</table>

</body>
</html>

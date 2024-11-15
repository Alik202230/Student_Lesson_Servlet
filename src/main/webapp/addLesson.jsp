<%--
  Created by IntelliJ IDEA.
  User: alik
  Date: 11/15/24
  Time: 10:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add Lesson Page</title>
</head>
<body>
<h1>Add Lesson</h1>
<a href="/lessons">Lessons</a><br>
<form action="/add-lesson" method="post">
  Name: <input type="text" name="name"><br>
  Duration: <input type="date" name="duration"><br>
  Lecturer Name: <input type="text" name="lecturer_name"><br>
  Price: <input type="number" name="price"><br>
  <input type="submit" value="ADD">
</form>
</body>
</html>

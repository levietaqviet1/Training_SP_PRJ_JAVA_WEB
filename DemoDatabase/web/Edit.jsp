 
<%@page import="model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.ClassRoom"%>
<%@page import="model.ClassRoom"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<ClassRoom> listClass = new ArrayList<ClassRoom>();
            if (request.getAttribute("list") != null) {
                listClass = (List<ClassRoom>) request.getAttribute("list");
            }
            Student student = new Student();
            if (request.getAttribute("student") != null) {
                student = (Student) request.getAttribute("student");
            }
        %>
        <h1>Edit student</h1>
        <form action="edit" method="get" >
            <input type="text" value="<%=student.getId()%>" name="id" hidden="" >
            Name : <input type="text" name="name" value="<%=student.getName()%>" ><br>
            Date : <input type="date" name="date" value="<%=student.getDate()%>"><br>
            Gender : <input type="radio" name="gender" value="nam" >Male
            <input type="radio" name="gender" value="nu" >Female
            <br>
            Class Room
            <select name="class" >
                <%
                    for (ClassRoom classRoom : listClass) {
                %>
                <option value="<%=classRoom.getId()%>" ><%=classRoom.getName()%></option>
                <%
                    }
                %>

            </select>
            <br>
            <input type="submit" value="Edit" >
        </form>
    </body>
</html>

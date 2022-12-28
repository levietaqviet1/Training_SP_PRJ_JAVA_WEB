 
<%
    if (session.getAttribute("confirmEmail_st") != null) {
            
        }else{
        response.sendRedirect("home");
    }
%>

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
        %>
        <h1>Hello : ${sessionScope.user.username}</h1>
        <h1>Add student</h1>
        <form action="add" method="get" >
            Name : <input type="text" name="name" ><br>
            Date : <input type="date" name="date" ><br>
            Gender : <input type="radio" name="gender" value="nam" >Male
            <input type="radio" name="gender" value="nu" >Female
            <br>
            Class Room
            <select name="class" >
                <%
                    for (ClassRoom classRoom : listClass) {
                %>
                <option value="<%=classRoom.getId()%>" ><%=classRoom.getName() %></option>
                <%
                    }
                %>

            </select>
            <br>
            <input type="submit" value="add" >
        </form>
    </body>
</html>

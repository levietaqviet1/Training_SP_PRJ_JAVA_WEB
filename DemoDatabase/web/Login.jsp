 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%
    if (request.getSession().getAttribute("user")!=null) {
            response.sendRedirect("list");
        }
    %>
        <h1>Login!</h1>
        <form action="login" method="post" >
            Username : <input type="text" name="username" value="${cookie.user.value}" ><br>
            Pass : <input type="text" name="password" value="${cookie.pass.value}" ><br>
            <input type="submit" value="Login" >
            ${requestScope.mess}
        </form>
    </body>
</html>

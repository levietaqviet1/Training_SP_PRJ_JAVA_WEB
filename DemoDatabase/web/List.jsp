 
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<Student> list = new ArrayList<Student>();
            if (request.getAttribute("list") != null) {
                list = (List<Student>) request.getAttribute("list");
            }
            User u = new User();
            if (request.getSession().getAttribute("user") != null) {
                u =(User) request.getSession().getAttribute("user");
            }
        %>
        <h1>Hello : <%=u.getUsername()%> 
            <a href="logout" ><button>Loggout</button> </a>
        </h1>
        <h1>List Student</h1>
        <a href="next-add" ><button>Add new</button> </a>
        
        <table border="1" >
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Date</td>
                <td>Gender</td>
                <td>Class name</td>
                <td>Operation</td>
            </tr>
            <%
                for (Student student : list) {
            %>
            <tr>
                <td><%=student.getId()%></td>
                <td><%=student.getName()%></td>
                <td><%=student.getDate()%></td>
                <td><%=student.isGender() ? "Nam" : "Nữ"%></td>
                <td><%=student.getClassRoom().getName()%></td>
                <td>
                    <a href="next-edit?id=<%=student.getId()%>" ><button>Edit</button></a>
                    <a href="delete?id=<%=student.getId()%>" ><button>Delete</button>
                </td>
            </tr>
            <%
                }
            %>

        </table>
    </body>
</html>

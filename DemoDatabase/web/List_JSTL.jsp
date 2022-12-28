 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello : <a href="logout" ><button>Loggout</button> </a> </h1>
        <h1>List Student</h1>  <a href="next-add" ><button>Add new</button> </a>
        <table border="1" >
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Date</td>
                <td>Gender</td>
                <td>Class name</td>
                <td>Operation</td>
            </tr>
            <c:forEach items="${requestScope.list}" var="f" >
                <tr>
                    <td>${f.id}</td>
                    <td>${f.name} || ${fn:toUpperCase(f.name)} </td>
                    <td>${f.date}</td>
                    <td>
                        <c:if test="${f.gender == true}">
                            <c:out value="Nam" />
                        </c:if>
                        <c:if test="${f.gender != true}">
                            <c:out value="Nữ" />
                        </c:if>
                    </td>
                    <td>${f.classRoom.name}</td>
                    <td>
                        <a href="next-edit?id=${f.id}" ><button>Edit</button></a>
                        <a href="delete?id=${f.id}" ><button>Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

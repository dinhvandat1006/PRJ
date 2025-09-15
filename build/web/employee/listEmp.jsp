<%-- 
    Document   : listEmp
    Created on : Sep 14, 2025, 5:48:11 PM
    Author     : datdi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách nhân viên</title>
    </head>
    <body>
        <h1>Danh sách nhân viên</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                 <th>Name</th>
                  <th>Email</th>
                   <th>Address</th>
                
            </tr>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.id}</td>
                        <td>${employee.name}</td>
                            <td>${employee.email}</td>
                                <td>${employee.address}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

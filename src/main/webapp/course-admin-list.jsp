<%-- 
    Document   : course-admin-list
    Created on : Mar 20, 2024, 2:21:47 PM
    Author     : namto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    
    <style>
        td {
            border: 1px solid #000;
            padding: 8px
        }
    </style>
</head>
<body>
    <%@include file="include/header.jsp" %>
    <a href="course-admin.jsp" style="text-decoration: none;">
        <button style="padding: 0 40px; margin: auto; display: block;">+ Create new course</button>
    </a>
    <table style="margin: 40px auto;">
        <th>
            <td>Title</td>
            <td>Price</td>
            <td>Category</td>
            <td>Operation</td>
        </th>
        <c:forEach var="course" items="${courses}">
            <tr>
                <td>${course.getId()}</td>
                <td>${course.getTitle()}</td>
                <td>${course.getPrice()}$</td>
                <td>
                    ${course.getCategories().size() > 0 ? course.getCategories().get(0).getName() : ""}
                    ${course.getCategories().size() > 1 ? "," : ""}
                    ${course.getCategories().size() > 1 ? course.getCategories().get(1).getName() : ""}
                    ${course.getCategories().size() > 2 ? ",..." : ""}
                </td>
                <td>
                    <a href="course-edit?courseId=${course.getId()}">Edit</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%@include file="include/footer.jsp" %>
</body>
</html>

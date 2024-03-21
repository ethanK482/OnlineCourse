<%-- 
    Document   : course-admin-edit
    Created on : Mar 20, 2024, 4:49:28 PM
    Author     : namto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <form method="POST" action="course-edit">
            <div>ID</div>
            <input name="courseId" style="width: 500px" value="${course.getId()}" readonly />
            <div>Title</div>
            <input name="title" style="width: 500px" value="${course.getTitle()}" />
            <div>Description</div>
            <textarea name="description" id="w3review" rows="4" cols="50" style="width: 500px; height: 100px;">${course.getDescription()}</textarea>
            <div>Price (USD)</div>
            <input name="price" style="width: 500px" value="${course.getPrice()}" />
            <div>Category</div>
            <textarea name="category" id="w3review" rows="4" cols="50" style="width: 500px; height: 100px;">${categories}</textarea>
            <div>Banner</div>
            <input name="banner" style="width: 500px" value="${course.getBannerUrl()}" />
            <div>Videos</div>
            <input name="videoCount" value="${videos.size()}" hidden />
            <table>
                <th>
                    <td>Title</td>
                    <td>Url</td>
                </th>
                <c:if test="${videos.size() > 0}">
                    <c:forEach var="i" begin="0" end="${videos.size() - 1}">
                        <tr>
                            <td>${i} <input name="videoId${i}" value="${videos.get(i).getId()}" hidden /></td>
                            <td><input name="videoTitle${i}" value="${videos.get(i).getTitle()}"/></td>
                            <td><input name="videoUrl${i}" value="${videos.get(i).getUrl()}" style="width: 500px    "/></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <br /><input type="submit" value="Complete Edit"/>
        </form>
        <%@include file="include/footer.jsp" %>
    </body>
</html>

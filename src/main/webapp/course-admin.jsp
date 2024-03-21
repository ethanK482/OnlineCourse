<%-- 
    Document   : create-course
    Created on : Mar 13, 2024, 5:16:35 PM
    Author     : namto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <form action="create-course" method="POST" style="margin: 40px">
            <div>Title</div>
            <input name="title" style="width: 500px" />
            <div>Description</div>
            <textarea name="description" id="w3review" rows="4" cols="50" style="width: 500px; height: 100px;"></textarea>
            <div>Price (USD)</div>
            <input name="price" style="width: 500px" />
            <div>Category</div>
            <textarea name="category" id="w3review" rows="4" cols="50" style="width: 500px; height: 100px;"></textarea>
            <div>Assets path</div>
            <input name="path" style="width: 500px" />
            <div>Videos</div>
            <textarea name="video" id="w3review" rows="4" cols="50" style="width: 500px; height: 100px;"></textarea>
            <br /><input type="submit" value="Create"/>
        </form>
        <%@include file="include/footer.jsp" %>
    </body>
</html>

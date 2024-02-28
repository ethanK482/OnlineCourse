<%-- 
    Document   : test
    Created on : Feb 28, 2024, 3:14:24 PM
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
    <form action="CloudinaryTest" method="POST" enctype="multipart/form-data">
        <input type="file" name="media" />
        <input type="submit" />
    </form>
    <img id="display" alt="your image"/>
</body>
</html>

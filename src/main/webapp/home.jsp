<%-- 
    Document   : home
    Created on : Feb 28, 2024, 1:59:57 PM
    Author     : HuyHK
--%>

<%@page import="model.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>COURSEVO - Online Courses HTML Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>
        <div class="container-fluid d-none d-lg-block">
            <div class="row align-items-center py-4 px-xl-5">
                <div class="col-lg-3">
                    <a href="" class="text-decoration-none">
                        <h1 class="m-0"><span class="text-primary">C</span>OURSEVO</h1>
                    </a>
                </div>
                <div class="col-lg-3 text-right">
                    <div class="d-inline-flex align-items-center">
                        <i class="fa fa-2x fa-map-marker-alt text-primary mr-3"></i>
                        <div class="text-left">
                            <h6 class="font-weight-semi-bold mb-1">Our Office</h6>
                            <small>123 Street, New York, USA</small>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 text-right">
                    <div class="d-inline-flex align-items-center">
                        <i class="fa fa-2x fa-envelope text-primary mr-3"></i>
                        <div class="text-left">
                            <h6 class="font-weight-semi-bold mb-1">Email Us</h6>
                            <small>info@example.com</small>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 text-right">
                    <div class="d-inline-flex align-items-center">
                        <i class="fa fa-2x fa-phone text-primary mr-3"></i>
                        <div class="text-left">
                            <h6 class="font-weight-semi-bold mb-1">Call Us</h6>
                            <small>+012 345 6789</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->


        <div class="container-fluid">
            <div class="row border-top px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a class="d-flex align-items-center justify-content-between bg-secondary w-100 text-decoration-none" data-toggle="collapse" href="#navbar-vertical" style="height: 67px; padding: 0 30px;">
                        <h5 class="text-primary m-0"><i class="fa fa-book-open mr-2"></i>Subjects</h5>
                        <i class="fa fa-angle-down text-primary"></i>
                    </a>
                    <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0 bg-light" id="navbar-vertical" style="width: calc(100% - 30px); z-index: 9;">
                        <div class="navbar-nav w-100">
                            <form
                                action="course-fetch"
                                style="margin: 12px"
                            >
                                <input name="courseTitle" placeholder="Enter course name..." style="width: 222px" />
                                <input type="submit" value="Search" style="border: none; color: white; background-color: #FF6600; padding: 4px 8px; border-radius: 6px"/>
                            </form>
                            <div style="max-height: 131px; overflow-y: scroll"
                            >
                                <% 
                                    String courseTitleParam = "";
                                    if (request.getParameter("courseTitle") != null)
                                    courseTitleParam = "courseTitle=" + request.getParameter("courseTitle");
                                %>
                                <c:forEach var="category" items="${categories}">
                                    <a 
                                        href="?<%=courseTitleParam%>&categoryId=${category.getId()}"
                                        class="nav-item nav-link"
                                    >${category.getName()}</a>
                                </c:forEach>
                            </div>
                        </div>
                    </nav>
                </div>
                <div class="col-lg-9">
                    <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                        <a href="" class="text-decoration-none d-block d-lg-none">
                            <h1 class="m-0"><span class="text-primary">E</span>COURSES</h1>
                        </a>
                        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                            <div class="navbar-nav py-0">
                                <a href="index.html" class="nav-item nav-link active">Home</a>
                                <a href="about.jsp" class="nav-item nav-link">About</a>
                                <a href="course-fetch?myLearning=1"
                                    class="nav-item nav-link"
                                >My Learning</a>
<!--                                <a 
                                    href="${user.getRole().equal("admin") ? "course-fetch-admin" : ""}" 
                                    class="nav-item nav-link"
                                >Teaches</a>-->
                                <a 
                                    href="course-fetch-admin"
                                    class="nav-item nav-link"
                                >Teaches</a>
                                <div class="nav-item dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Blog</a>
                                    <div class="dropdown-menu rounded-0 m-0">
                                        <a href="blog.html" class="dropdown-item">Blog List</a>
                                        <a href="single.html" class="dropdown-item">Blog Detail</a>
                                    </div>
                                </div>
                                <a href="contact.html" class="nav-item nav-link">Contact</a>
                            </div>
                            <div class="d-flex align-items-center "> 
                                <c:if test="${user==null}">
                                    <a class="btn btn-primary py-2 px-4  d-none d-lg-block" href="login.jsp">Login</a>
                                    <a class="btn btn-primary py-2 px-4 ml-2  d-none d-lg-block" href="register.jsp">Register</a>
                                </c:if>
                                 <c:if test="${user!=null}">
                                    <span class="btn btn-primary py-2 px-4  d-none d-lg-block">${user.firstName} ${user.lastName}</span>
                                    
                                </c:if>
                                   


                                <a class="btn  btn-primary py-2 px-4 ml-2  d-none d-lg-block" href="cart?action=view-cart">Cart (${cartSize})</a>
                                  <c:if test="${user!=null}">
                                         <a class="ml-3 btn btn-primary py-2 px-4 " href="auth?action=logout">Logout </a>
                                    
                                </c:if>
                            </div>

                        </div>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Navbar End -->







        <!-- Courses Start -->
        <div class="container-fluid py-5">
            <div class="container py-5">
                <div class="row">
                    <c:forEach var="course" items="${courses}">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="rounded overflow-hidden mb-2">
                                <img class="img-fluid" src="img/course-1.jpg" alt="">
                                <div 
                                    class="bg-secondary p-4"
                                    style="
                                    border-radius: 10px;
                                    "
                                    >
                                    <img 
                                        src="${course.getBannerUrl()}"
                                        style="
                                            width: 300px;
                                            height: 200px;
                                            object-fit: cover;
                                            margin-bottom: 16px;
                                            border-radius: 10px;
                                        "
                                    />
                                    <a class="h5" href="course-detail?courseId=${course.getId()}">${course.getTitle()}</a>
                                    <br />
                                    <c:if test="${course.getCategories().size() > 0}">
                                        <span>${course.getCategories().get(0).getName()}</span>
                                    </c:if>
                                    <c:if test="${course.getCategories().size() > 1}">
                                        <span>, ${course.getCategories().get(1).getName()}</span>
                                    </c:if>
                                    <div class="border-top pt-4">
                                        <div class="d-flex justify-content-between" style="margin-bottom: 12px">
                                            <h6 class="m-0"><i class="fa fa-pen-nib"></i>
                                                ${course.getSeller().getFirstName()} ${course.getSeller().getLastName()}
                                            </h6>
                                            <h6 class="m-0">
                                                <span>$</span>
                                                ${course.getPrice()}
                                            </h6>
                                            <!--<h5 class="m-0">$99</h5>-->
                                        </div>
                                        <a class="btn btn-outline-dark mt-auto" href="cart?action=add-to-cart&id=${course.id}">Add to cart</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- Courses End -->

        <div style="
             display: flex;
             justify-content: space-between;
             margin-top: -120px;
        ">
            <a 
                ${prevPage == null ? "" : prevPage}
                style="
                    display: block;
                    margin: 40px;
                    margin-left: 120px;
                "
            >Previous page</a>
            <a 
                ${nextPage == null ? "" : nextPage}
                style="
                    display: block;
                    margin: 40px;
                    margin-right: 120px;
                "
            >Next page</a>
        </div>
                
        <%@include file="include/footer.jsp" %>

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="fa fa-angle-double-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>
</html>

<%-- 
    Document   : cart
    Created on : Mar 5, 2024, 9:02:44 PM
    Author     : HuyHK
--%>
<%@include file="include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/cart.css" rel="stylesheet">
    </head>
    <body>
        <section class="h-100 gradient-custom">
            <div class="container py-5">
                <div class="row d-flex justify-content-center my-4">
                    <div class="col-md-8">
                        <div class="card mb-4">
                            <div class="card-header py-3">
                                <h5 class="mb-0">Cart - ${quanlity} items</h5>
                            </div>
                            <div class="card-body">
                                <!-- Single item -->
                                
                                
                                <c:forEach var="course" items="${courses}">
                        <div class="row">
                                    <div class="col-lg-3 col-md-12 mb-4 mb-lg-0">
                                        <div class="bg-image hover-overlay hover-zoom ripple rounded" data-mdb-ripple-color="light">
                                            <img src="${course.bannerUrl}"
                                                 class="w-100" alt="Course" />
                                            <a href="#!">
                                                <div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>
                                            </a>
                                        </div>
                                        <!-- Image -->
                                    </div>

                                    <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">
                                        <p><strong>${course.title}</strong></p>
                                        <p>${course.seller.firstName} ${course.seller.lastName}</p>
                                        <button type="button" class="btn btn-primary btn-sm me-1 mb-2" data-mdb-toggle="tooltip"
                                                title="Remove item">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </div>

                                    <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
                                        <p class="text-start text-md-center">
                                            <strong>${course.price}$</strong>
                                        </p>
                                        <!-- Price -->
                                    </div>
                                </div>
                    </c:forEach>
                               
                                <!-- Single item -->

                                <hr class="my-4" />

                                <!-- Single item -->
                              
                                <!-- Single item -->
                            </div>
                        </div>
                      
                        <div class="card mb-4 mb-lg-0">
                            <div class="card-body">
                                <p><strong>We accept</strong></p>
                                <img class="me-2" width="45px"
                                     src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"
                                     alt="Visa" />
                            
                                <img class="me-2" width="45px"
                                     src="https://logos-world.net/wp-content/uploads/2020/07/PayPal-Logo-500x281.png"
                                     alt="PayPal acceptance mark" />
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-header py-3">
                                <h5 class="mb-0">Summary</h5>
                            </div>
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <li
                                        class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                                        Courses
                                        <span>${totalPrice}$</span>
                                    </li>
                                    <li
                                        class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                                        <div>
                                            <strong>Total amount</strong>
                                            <strong>
                                                <p class="mb-0">(including VAT)</p>
                                            </strong>
                                        </div>
                                        <span><strong>${totalPrice}$</strong></span>
                                    </li>
                                </ul>
                                    
                                    <form action="Order?action=pay" method="post">
                                        <input value="${totalPrice}" name="totalPrice" type="hidden"/>
                                         <button type="submit" class="btn btn-primary btn-lg btn-block">
                                             Go to checkout</button>
                                    </form>
                                   
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
    <%@include file="include/footer.jsp" %>
</html>
<%-- 
    Document   : login
    Created on : Feb 28, 2024, 1:29:45 PM
    Author     : HuyHK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login V5</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <link href="css/style.css" rel="stylesheet">
        <!--===============================================================================================-->
    </head>
    <body>

        <div class="limiter">
            <div class="container-login100" style="background-image: url('images/bg-01.jpg');">
                <div class="wrap-login100 p-l-110 p-r-110 p-t-30 p-b-33">
                    <form method="post" action="register" class="login100-form validate-form flex-sb flex-w">
                        <span class="login100-form-title">
                            Sign Up
                        </span>

                        <!--					<a href="#" class="btn-face m-b-20">
                                                                        <i class="fa fa-facebook-official"></i>
                                                                        Facebook
                                                                </a>
                        
                                                                <a href="#" class="btn-google m-b-20">
                                                                        <img src="images/icons/icon-google.png" alt="GOOGLE">
                                                                        Google
                                                                </a>-->
                        <div class="p-t-31 p-b-9">
                            <span class="txt1">
                                First name
                            </span>
                        </div>	
                        <div style="height: 50px" class="wrap-input100 validate-input" data-validate = "First name is required">
                            <input class="input100" type="text" name="firstname" >
                            <span class="focus-input100"></span>
                        </div>
                        <div class="p-t-31 p-b-9">
                            <span class="txt1">
                                Last name
                            </span>
                        </div>
                        <div style="height: 50px" class="wrap-input100 validate-input" data-validate = "Last name is required">
                            <input class="input100" type="text" name="lastname" >
                            <span class="focus-input100"></span>
                        </div>
                        <div class="p-t-31 p-b-9">
                            <span class="txt1">
                                Email
                            </span>
                        </div>
                        <div style="height: 50px" class="wrap-input100 validate-input" data-validate = "Username is required">
                            <input type="email" class="input100" type="text" name="email" >
                            <span class="focus-input100"></span>
                        </div>

                        <div class="p-t-13 p-b-9">
                            <span class="txt1">
                                Password
                            </span>


                        </div>
                        <div style="height: 50px" class="wrap-input100 validate-input" data-validate = "Password is required">
<!--                            <input class="input100" type="password" name="password" >-->
                            
                            <input  class="input100" type="password" id="password" name="password" pattern="^(?=.*[A-Z]).{8,}$" title="Password must contain at least one uppercase letter and be at least 8 characters long" required>
                            <span class="focus-input100"></span>
                        </div>
                        <div class="p-t-13 p-b-9">
                            <span class="txt1">
                                Confirm Password
                            </span>


                        </div>
                        <div style="height: 50px" class="wrap-input100 validate-input" data-validate = "Password is required">
                            <input class="input100" type="password" name="confirm_password" >
                            <span class="focus-input100"></span>
                        </div>

                        <div class="container-login100-form-btn m-t-17">
                            <button class="login100-form-btn">
                                Sign up
                            </button>
                        </div>


                    </form>
                </div>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>

        <div id="dropDownSelect1"></div>

        <!--===============================================================================================-->
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/daterangepicker/moment.min.js"></script>
        <script src="vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script src="js/main.js"></script>

    </body>
</html>

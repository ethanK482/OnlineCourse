package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.User;
import service.UserService;
import util.HashPassword;
import util.JWTProvider;
import util.SendMail;

@WebServlet(name = "Auth", urlPatterns = {"/auth"})
public class Auth extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getParameter("action")) {
            case "login":
                login(request, response);
                break;
            case "register":
                register(request, response);
                break;
            case "verify": {
                verifyHandle(request, response);
                break;
            }
            case "logout":{
                Cookie cookie = new Cookie("auth_token", "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                response.sendRedirect("course-fetch");
                break;
            }
            default:
                throw new AssertionError();
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException, ServletException {
        ArrayList userInfo = UserService.login(request.getParameter("email"), HashPassword.getSecurePassword(request.getParameter("password")));
        if (userInfo.isEmpty()) {
            response.sendError(401, "Wrong email or password");
        } else {
            Account account = (Account) userInfo.get(0);
            User user = (User) userInfo.get(1);
            if (account.isIsVerifyEmail()) {
                Cookie cookie = new Cookie("auth_token", JWTProvider.generateToken(account.getId(), user.getRole()));
                cookie.setMaxAge(60*60*24*30);
                response.addCookie(cookie);
                response.sendRedirect("course-fetch");
                return;
            }
            request.setAttribute("email", account.getEmail());
            verifyEmail(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        if (!password.equals(confirmPassword)) {
            response.sendError(400, "password not match");
        }
        String email = request.getParameter("email");
        boolean isExistUser = UserService.findAccountByEmail(email);
        if (isExistUser) {
            response.sendError(400, "User already exist");
        } else {
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            boolean isRegister = UserService.register(email, HashPassword.getSecurePassword(password), firstName, lastName);
            if (isRegister) {
                verifyEmail(request, response);
            } else {
                response.sendError(500, "Something went wrong");
            }
        }
    }

    private void verifyEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        Random rd = new Random();
        int randomNumber = Math.abs(rd.nextInt());
        boolean isSendEmail = SendMail.send("Your verify number is: " + randomNumber, "Verify email", email);
        if (isSendEmail) {
            request.setAttribute("email", email);
            request.setAttribute("verifyToken", HashPassword.getSecurePassword(randomNumber + ""));
            request.getRequestDispatcher("verify.jsp").forward(request, response);
        } else {
            response.sendError(500, "Can't send email");
        }
    }

    private void verifyHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String secretNumber = request.getParameter("secret");
        String userSend = request.getParameter("verifyToken");
        String email = request.getParameter("email");
        boolean isMatch = HashPassword.verifyPassword(userSend, secretNumber);
        if (isMatch) {
            boolean isUpdate = UserService.updateVerifyStatus(email);
            if (!isUpdate) {
                response.sendError(500, "Something went wrong please verify again");
            } else {
                request.setAttribute("notice", "Verify email susscessfully. You can login!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            response.sendError(400, "Secret number not match");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.UserService;
import util.HashPassword;

@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
            System.out.println(isRegister);
            if (isRegister) {
                request.getRequestDispatcher("verify-email").forward(request, response);
            } else {
                response.sendError(500, "Something went wrong");
            }
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
    }

}

package controller;

import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ArrayList userInfo = UserService.login(request.getParameter("email"), HashPassword.getSecurePassword(request.getParameter("password")));
        if (userInfo.isEmpty()  ){
            response.sendError(401, "Wrong email or password");
        } else {
            Account account = (Account) userInfo.get(0);
            User user = (User) userInfo.get(1);
            if (account.isIsVerifyEmail()) {
                Cookie cookie = new Cookie("auth_token", JWTProvider.generateToken(account.getId(), user.getRole()));
                response.addCookie(cookie);
                response.sendRedirect("course-fetch");
                return;
            }
            request.setAttribute("firstname", user.getFirstName());
            request.setAttribute("email", account.getEmail());           
            request.setAttribute("lastname", user.getLastName());
            request.getRequestDispatcher("verify-email").forward(request, response);
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

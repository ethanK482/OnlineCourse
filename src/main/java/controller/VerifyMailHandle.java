/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.UserService;
import util.HashPassword;

/**
 *
 * @author HuyHK
 */
@WebServlet(name = "VerifyMailHandle", urlPatterns = {"/verify-handle"})
public class VerifyMailHandle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
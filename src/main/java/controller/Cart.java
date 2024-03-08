package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Course;
import model.User;
import service.CourseService;
import service.UserService;
import util.CookieProvide;

/**
 *
 * @author HuyHK
 */
@WebServlet(name = "Cart", urlPatterns = {"/cart"})
public class Cart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getParameter("action")) {
            case "add-to-cart":
                addToCart(request, response);
                break;
            case "view-cart":
                viewCart(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            Cookie[] cookies = request.getCookies();
            StringBuilder cartContentBuilder = CookieProvide.getCarts(cookies);
            if (cartContentBuilder.length() > 0) {
                cartContentBuilder.append(id);
            } else {
                if (!cartContentBuilder.toString().contains(id)) {
                    cartContentBuilder.append("-").append(id);
                }
            }
            String cartContent = cartContentBuilder.toString();
            Cookie c = new Cookie("cart", cartContent);
            c.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(c);
            response.sendRedirect("course-fetch");
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void viewCart(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String cartString = CookieProvide.getCarts(cookies).toString();
        ArrayList cartItems = new ArrayList();
        String[] cartArr = cartString.split("-");
        double totalPrice = 0;
        for (String courseId : cartArr) {
            Course course  =   CourseService.fetchCourseById(Integer.parseInt(courseId));
            User seller = UserService.getUserById(course.getSeller().getuId());
            course.getSeller().setFirstName(seller.getFirstName());
            course.getSeller().setLastName(seller.getLastName());
            cartItems.add(course);
            totalPrice+=course.getPrice();
        }
        request.setAttribute("courses", cartItems);
         request.setAttribute("quanlity", cartItems.size());
         request.setAttribute("totalPrice", totalPrice);
         
        try {
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
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

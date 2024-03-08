/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Course;
import service.CourseService;
import util.CookieProvide;

@WebServlet(name="CourseFetch", urlPatterns={"/course-fetch"})
public class CourseFetch extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp");
        dispatch.forward(request, response);
    } 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ArrayList<Course> courses = CourseService.fetchCourses();
        request.setAttribute("courses", courses);
         Cookie[] cookies = request.getCookies();
        StringBuilder cartString = CookieProvide.getCarts(cookies);
        request.setAttribute("cartSize", 0);
        if(cartString.length() > 0){
            String[] cartArr = cartString.toString().split("-");     
        request.setAttribute("cartSize", cartArr.length);
        }
        
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Course;
import model.User;
import model.Video;
import service.CourseService;
import util.CookieProvide;

@WebServlet(name="CourseDetail", urlPatterns={"/course-detail"})
public class CourseDetail extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatch = request.getRequestDispatcher("course-detail.jsp");
        dispatch.forward(request, response);
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        User user = CookieProvide.getUserInfo(request);
        boolean access = false;
        
        Course course = CourseService.fetchCourseById(Integer.parseInt(courseId));
        ArrayList<Video> videos = CourseService.fetchVideoByCourseId(courseId);
        if (user != null && CourseService.checkUserCourse(courseId, String.valueOf(user.getuId()))) access = true;
        
        request.setAttribute("videos", videos);
        request.setAttribute("course", course);
        request.setAttribute("access", access);

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

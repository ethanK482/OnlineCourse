/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Course;
import model.User;
import service.CourseService;
import service.UserService;
import util.CookieProvide;

@WebServlet(name = "CourseFetch", urlPatterns = {"/course-fetch"})
public class CourseFetch extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp");
        dispatch.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageParam = request.getParameter("page");
        String myLearningParam = request.getParameter("myLearning");
        String courseTitleParam = request.getParameter("courseTitle");
        String categoryIdParam = request.getParameter("categoryId");
        String nextPageParamUrl = "?";
        String prevPageParamUrl = "?";
        int page = 1;
        String courseTitle = "";
        String categoryId = "";
        User user = CookieProvide.getUserInfo(request);
        String userId = "";
        
        if (myLearningParam != null && user == null) {
            response.sendRedirect("about.jsp");
            return;
        }
        
        if (user != null && myLearningParam != null) userId = String.valueOf(user.getuId());
        
        ArrayList<Category> categories = CourseService.fetchListCategory();
        request.setAttribute("categories", categories);
        
//        if (userIdParam != null) userId = userIdParam;
        
        if (pageParam != null) page = Integer.parseInt(pageParam);
        if (courseTitleParam != null) {
            courseTitle = courseTitleParam;
            nextPageParamUrl = nextPageParamUrl + "courseTitle=" + courseTitle +"";
            prevPageParamUrl = prevPageParamUrl + "courseTitle=" + courseTitle +"";
        }
        
        if (categoryIdParam != null) categoryId = categoryIdParam;
        
        ArrayList<Course> courses = CourseService.fetchCourses(courseTitle, categoryId, userId);
        request.setAttribute("courses", courses.subList((page - 1) * 12, Math.min(page * 12, courses.size())));
        
        int maxPage = courses.size() / 12;
        if (courses.size() % 12 > 0) ++maxPage;
        
        if (page > 1) {
            if (prevPageParamUrl.length() > 1) prevPageParamUrl = prevPageParamUrl + "&";
            prevPageParamUrl = prevPageParamUrl + "page=" + (page - 1);
            request.setAttribute("prevPage", "href=\"" + prevPageParamUrl + "\"");
        }
        if (page < maxPage) {
            if (nextPageParamUrl.length() > 1) nextPageParamUrl = nextPageParamUrl + "&";
            nextPageParamUrl = nextPageParamUrl + "page=" + (page + 1);
            request.setAttribute("nextPage", "href=\"" + nextPageParamUrl + "\"");
        }
        
        Cookie[] cookies = request.getCookies();
        StringBuilder cartString = CookieProvide.getCarts(cookies);
        request.setAttribute("cartSize", 0);
        if (cartString.length() > 0) {
            String[] cartArr = cartString.toString().split("-");
            request.setAttribute("cartSize", cartArr.length);
        }
        request.setAttribute("user", user);
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

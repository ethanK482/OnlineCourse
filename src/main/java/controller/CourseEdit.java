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
import model.Category;
import model.Course;
import model.Video;
import service.CourseService;

@WebServlet(name="CourseEdit", urlPatterns={"/course-edit"})
public class CourseEdit extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CourseEdit</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseEdit at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        
        Course course = CourseService.fetchCourseById(Integer.parseInt(courseId));
        request.setAttribute("course", course);
        
        ArrayList<Category> categories = CourseService.fetchCategoryByCourseId(courseId);
        String categoriesText = "";
        if (!categories.isEmpty()) categoriesText = categoriesText + categories.get(0).getName();
        for (int i = 1 ; i < categories.size(); ++i) categoriesText = categoriesText + "\n" + categories.get(i).getName();
        request.setAttribute("categories", categoriesText);
        
        ArrayList<Video> videos = CourseService.fetchVideoByCourseId(courseId);
        request.setAttribute("videos", videos);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("course-admin-edit.jsp");
        dispatcher.forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        String courseTitle = request.getParameter("title");
        String courseDescription = request.getParameter("description");
        String coursePrice = request.getParameter("price");
        String[] categoryText = request.getParameter("category").split("\r\n");
        String bannerUrl = request.getParameter("banner");
        String videoCount = request.getParameter("videoCount");
        
        CourseService.deleteCourseCategoryRelationShipByCourseId(courseId);
        
        for (int i = 0; i < categoryText.length; ++i) {
            Category category = CourseService.createCategory(categoryText[i]);
            CourseService.createCourseCategoryRelationship(courseId, String.valueOf(category.getId()));
        }
        
        for (int i = 0; i < Integer.parseInt(videoCount); ++i) 
            CourseService.updateVideo(
                    request.getParameter("videoId" + i),
                    request.getParameter("videoTitle" + i),
                    request.getParameter("videoUrl" + i)
            );
        
        CourseService.updateCourse(courseId, courseTitle, courseDescription, bannerUrl, coursePrice);
        
        response.sendRedirect("course-detail?courseId=" + courseId);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

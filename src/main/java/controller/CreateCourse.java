/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CourseService;
import model.Category;
import model.Course;
import service.CloudinaryService;

@WebServlet(name="CreateCourse", urlPatterns={"/create-course"})
public class CreateCourse extends HttpServlet {
   
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
            out.println("<title>Servlet CreateCourse</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateCourse at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.out.println("get");
        processRequest(request, response);
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String category = request.getParameter("category");
        String path = request.getParameter("path");
        String video = request.getParameter("video");
        
        String bannerUrl = CloudinaryService.uploadMedia(path + "\\banner.png");
        
        String courseId = CourseService.createCourse(title, description, price, bannerUrl, category);
        
        String[] categories = category.split("\r\n");
        for (int i = 0; i < categories.length; ++i) {
            Category createCategory = CourseService.createCategory(categories[i]);
            CourseService.createCourseCategoryRelationship(
                    String.valueOf(courseId), 
                    String.valueOf(createCategory.getId())
            );
        }
        
        String[] videosTitle = video.split("\r\n");
        for (int i = 0; i < videosTitle.length; ++i) {
            String videoUrl = CloudinaryService.uploadMedia(path + "\\" + (i + 1) + ".mp4");
            CourseService.createVideo(courseId, videosTitle[i], videoUrl);
        }
        
        //
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

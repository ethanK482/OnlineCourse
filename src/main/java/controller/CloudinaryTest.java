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
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.http.HttpRequest;

@WebServlet(name = "CloudinaryTest", urlPatterns = {"/CloudinaryTest"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class CloudinaryTest extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet CloudinaryTest</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CloudinaryTest at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
//        System.out.println(request.getParameter("image"));
        
//        try {
//            Dotenv dotenv = Dotenv.load();
//            Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
//            
//            cloudinary.config.secure = true;
//            System.out.println(cloudinary.config.cloudName);
//            
//            System.out.println(
//                cloudinary.uploader().upload(
//                    (File)request.getParameter("image"), 
//                    ObjectUtils.asMap("resource_type", "auto")
//                )
//            );
//        } catch (Exception e) { System.err.println(e); }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private String uploadMedia(HttpServletRequest request) {
        try {
            Part part = request.getPart("media");
            String realPath = request.getServletContext().getRealPath("/tmp");
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            if (!Files.exists(Paths.get(realPath))) Files.createDirectory(Paths.get(realPath));
            part.write(realPath + "/" + fileName);
        
        
            Dotenv dotenv = Dotenv.load();
            Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
            cloudinary.config.secure = true;
            
            String result = cloudinary.uploader().upload(
                realPath + "/" + fileName,
                ObjectUtils.asMap("resource_type", "auto")
            ).get("secure_url").toString();
            
            new File(realPath + "/" + fileName).delete();
            
            return result;
        } catch (Exception e) { System.err.println(e); }
        
        return "";
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(uploadMedia(request));
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

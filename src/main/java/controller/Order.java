package controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import middleware.MiddleWare;
import model.Course;
import model.User;
import service.CourseService;
import service.OrderService;
import service.UserService;
import util.CookieProvide;

@WebServlet(name = "Order", urlPatterns = {"/Order"})
public class Order extends HttpServlet {
    private String endcodeOrderId(int orderId) {
        return Base64.getEncoder().encodeToString((orderId+"").getBytes());
    }

    private String decodeOrderId(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       if(!MiddleWare.authMiddleWare(request, response)){
          response.sendRedirect("login.jsp");
          return;
       }
        switch (request.getParameter("action")) {
            case "pay":
                Pay(request, response);
                break;
            case "success": {
                handleSuccess(request, response);
                break;
            }
            default:
                throw new AssertionError();
        }
    }
    private void handleSuccess(HttpServletRequest request, HttpServletResponse response){
     
        int orderId = Integer.parseInt(decodeOrderId(request.getParameter("pay-code")));
        OrderService.updatePayStatus(orderId);
        Cookie cookie = new Cookie("cart", "");
        response.addCookie(cookie);
        try {
            response.sendRedirect("course-fetch");
            return;
        } catch (IOException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    private void Pay(HttpServletRequest request, HttpServletResponse response) {
           User user  =  CookieProvide.getUserInfo(request);
             double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
            model.Order order = OrderService.createOrder(totalPrice, user.getuId());
            
             Cookie[] cookies = request.getCookies();
        String cartString = CookieProvide.getCarts(cookies).toString();
        String[] cartArr = cartString.split("-");
        for (String courseId : cartArr) {
            Course course  =   CourseService.fetchCourseById(Integer.parseInt(courseId));
            OrderService.createOrderItem(course.getId(),order.getId() );
        }
        try {
            Stripe.apiKey = "sk_test_51NhOiqFwiLeydcwjFiac5xebXeAJlL1ygtESPrNpTKU6TuyCHpdl34n4iOp3u99Guofm9JB2EEaZHL9qeVQ2UXaf00aB8IWj6f";
          
            SessionCreateParams params
                    = SessionCreateParams.builder()
                            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl("http://localhost:8080/OnlineCourse/Order?action=success&pay-code="+ endcodeOrderId(order.getId()))
                            .setCancelUrl("http://yourdomain.com/cancel.jsp")
                            .addLineItem(
                                    SessionCreateParams.LineItem.builder()
                                            .setQuantity(1L)
                                            .setPriceData(
                                                    SessionCreateParams.LineItem.PriceData.builder()
                                                            .setCurrency("usd")
                                                            .setUnitAmountDecimal(new BigDecimal(totalPrice).multiply(new BigDecimal(100)))
                                                            .setProductData(
                                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                            .setName("COURSEVO order")
                                                                            .build()
                                                            )
                                                            .build()
                                            )
                                            .build()
                            )
                            .build();

            Session session = Session.create(params);
            response.sendRedirect(session.getUrl());
        } catch (StripeException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
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

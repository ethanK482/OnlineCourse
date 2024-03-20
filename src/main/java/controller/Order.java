package controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
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
import service.OrderService;
import util.CookieProvide;
import util.EncodeProvider;

@WebServlet(name = "Order", urlPatterns = {"/Order"})
public class Order extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
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

    private void handleSuccess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int orderId = Integer.parseInt(EncodeProvider.decode(request.getParameter("pay-code")));
        int uid = CookieProvide.getUserInfo(request).getuId();
        ArrayList<Integer> courseIds = OrderService.findOrderItemByOrder(orderId);
        if (courseIds != null) {
            System.out.println(courseIds);
            for (int courseID : courseIds) {
                OrderService.addUserCourse(uid, courseID);
            }
        }
        OrderService.updatePayStatus(orderId);
        Cookie cookie = new Cookie("cart", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        try {
            response.sendRedirect("course-fetch");
        } catch (IOException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Pay(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        User user = CookieProvide.getUserInfo(request);
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        model.Order order = OrderService.createOrder(totalPrice, user.getuId());
        Cookie[] cookies = request.getCookies();
        String cartString = CookieProvide.getCarts(cookies).toString();
        String[] cartArr = cartString.split("-");
        for (String courseId : cartArr) {
            ArrayList<Integer> courseIds = OrderService.findUserCourse(user.getuId());
            if (courseIds.contains(Integer.valueOf(courseId))) {
                response.sendError(400, "You can't buy a course 2 time");
                return;
            }
            Course course = CourseService.fetchCourseById(Integer.parseInt(courseId));
            OrderService.createOrderItem(course.getId(), order.getId());
        }
        try {
            Stripe.apiKey = "sk_test_51NhOiqFwiLeydcwjFiac5xebXeAJlL1ygtESPrNpTKU6TuyCHpdl34n4iOp3u99Guofm9JB2EEaZHL9qeVQ2UXaf00aB8IWj6f";

            SessionCreateParams params
                    = SessionCreateParams.builder()
                            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl("http://localhost:8080/OnlineCourse/Order?action=success&pay-code=" + EncodeProvider.endcode(order.getId() + "") + "-" + EncodeProvider.endcode("secret"))
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
        } catch (StripeException | IOException ex) {
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
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

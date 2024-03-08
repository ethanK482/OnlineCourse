package middleware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import util.CookieProvide;

public class MiddleWare {
    
    public static boolean authMiddleWare(HttpServletRequest request, HttpServletResponse response) {
        User user = CookieProvide.getUserInfo(request);
        return user != null;
    }
    
    public static boolean adminMiddleWare(HttpServletRequest request, HttpServletResponse response) {
        User user = CookieProvide.getUserInfo(request);
        return user.getRole().equals("admin");
    }
}

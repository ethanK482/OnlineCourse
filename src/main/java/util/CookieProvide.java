package util;

import com.auth0.jwt.interfaces.Claim;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import model.User;
import service.UserService;

public class CookieProvide {

    public static StringBuilder getCarts(Cookie[] arr) {
        StringBuilder cartContentBuilder = new StringBuilder();
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    cartContentBuilder.append(o.getValue());
                }
            }
            return cartContentBuilder;
        }
        return new StringBuilder();
    }
    
    public static String removeAndGetNewCart(StringBuilder oldCart, String id){
     String[] cartArr =    oldCart.toString().split("-");
     if(cartArr.length ==1)return "";
     ArrayList list = new ArrayList();
     list.addAll(Arrays.asList(cartArr));
    for(int i=0; i< list.size(); i++){
        if(list.get(i).equals(id)) {
         list.remove(i);
        }
    }
    StringBuilder newCart = new StringBuilder();
    for(int i=0; i< list.size(); i++){
         newCart.append(list.get(i));
            if (i < list.size() - 1) {
                newCart.append("-");
            }
    }
    return newCart.toString();
    }
    
    public static User getUserInfo(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth_token")) {
                String token = cookie.getValue();
                Map<String, Claim> infoMap = JWTProvider.validateToken(token);
                int accountId = Integer.parseInt(infoMap.get("sub").toString().substring(1, infoMap.get("sub").toString().length() - 1));
                User user = UserService.getUserInfo(accountId);
                return user;
            }
        }
        return null;
    }
    public static void setHeaderInfo(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        StringBuilder cartString = CookieProvide.getCarts(cookies);
        request.setAttribute("cartSize", 0);
        if (!cartString.isEmpty()) {
            String[] cartArr = cartString.toString().split("-");
            request.setAttribute("cartSize", cartArr.length);
        }
        User user = CookieProvide.getUserInfo(request);
        request.setAttribute("user", user);
    }

    public static void main(String[] args) {

    }

}

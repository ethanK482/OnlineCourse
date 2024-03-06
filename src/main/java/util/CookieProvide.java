package util;

import javax.servlet.http.Cookie;

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

}

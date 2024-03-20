package util;

import java.util.Base64;

public class EncodeProvider {
    public static String endcode(String content) {
        return Base64.getEncoder().encodeToString((content).getBytes());
    }

    public static String decode(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString.split("-")[0]);
        return new String(decodedBytes);
    }
}

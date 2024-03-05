package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {

    private static final String SALT = "salt";

    public static String getSecurePassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(SALT.getBytes());
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static boolean verifyPassword(String inputPassword, String hashedPassword) {
        String inputHashedPassword = getSecurePassword(inputPassword);
        return inputHashedPassword.equals(hashedPassword);
    }

    public static void main(String[] args) {
        String password = "password123";
        String hashedPassword = getSecurePassword(password);
        System.out.println(hashedPassword);
    }
}

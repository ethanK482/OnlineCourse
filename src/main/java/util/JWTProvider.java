package util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTProvider {

    private static final String JWT_SECRET = "lodaaaaaa";
    private static Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
    public static String generateToken(int userID, String role) {

        String token = JWT.create()
                .withIssuer("online_course")
                .withSubject(userID + "")
                .withClaim("role", role)
                .sign(algorithm);
        return token;
    }

    public static boolean validateToken(String authToken) {
        try {
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("online_course") // Make sure issuer matches
                    .build()
                    .verify(authToken);

            System.out.println("Token verified. Subject: " +  jwt.getClaims());
        } catch (JWTDecodeException e) {
            // Invalid token
            System.out.println("Invalid token: " + e.getMessage());
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(JWTProvider.generateToken(1, "admin"));
       JWTProvider.validateToken(JWTProvider.generateToken(1, "admin"));
    }   
}

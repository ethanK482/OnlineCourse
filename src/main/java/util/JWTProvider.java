package util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTProvider {

    private final String JWT_SECRET = "lodaaaaaa";
    private Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
    public  String generateToken(String userID, String role) {

        String token = JWT.create()
                .withIssuer("online_course")
                .withSubject(userID)
                .withClaim("role", role)
                .sign(algorithm);
        return token;
    }

    public boolean validateToken(String authToken) {
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
        JWTProvider jwt = new JWTProvider();
        System.out.println(jwt.generateToken("123", "admin"));
       jwt.validateToken(jwt.generateToken("123", "admin"));
    }   
}

package util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Map;

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

    public static Map<String, Claim> validateToken(String authToken) {
        try {
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("online_course") // Make sure issuer matches
                    .build()
                    .verify(authToken);

            return jwt.getClaims();
        } catch (JWTDecodeException e) {
            // Invalid token
            System.out.println("Invalid token: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(JWTProvider.validateToken(JWTProvider.generateToken(1, "admin")));
    }
}

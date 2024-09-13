package experiments;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class TokenGenerator {

    static String secretKey = "zdhSDSsfnfSZfED456263642343SxcDSspfmz";

    public static void main(String[] args) {
       String generatedToken = generateToken("David","admin",secretKey);
        System.out.println(generatedToken);
       verifyToken(generatedToken,secretKey);

    }

    public static String generateToken(String username, String role, String secretKey){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String token = JWT.create()
                .withIssuer("myCompany")
                .withSubject(username)
                .withClaim("role",role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+3600*1000))
                .sign(algorithm);
        return token;
    }


    public static void verifyToken(String token,String secretKey){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("myCompany")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String subject = decodedJWT.getSubject();
            String role = decodedJWT.getClaim("role").toString();
            Date issuedAt = decodedJWT.getIssuedAt();
            Date expiresAt = decodedJWT.getExpiresAt();
            System.out.println("Subject: " + subject);
            System.out.println("Role: "+ role);
            System.out.println("Issued at: " + issuedAt);
            System.out.println("Expires at: "+expiresAt);


        }catch (Exception e) {
            System.out.println("Invalid token....");
        }
    }
}

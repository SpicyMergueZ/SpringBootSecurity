package config;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;



import org.springframework.boot.autoconfigure.ssl.SslBundleProperties.Key;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {


    private static final String SECRET_KEY= "576D5A7134743777217A25432A462D4A614E635266556A586E3272357538782F";

    public String extractUsername(String token) {

        return extractClaim(token, Claims:: getSubject);
    }

    public String generateToken(Object user){

        return generateToken(new HashMap<>(), user);
    }
   private String generateToken(HashMap<Object, Object> hashMap, Object user) {
        return null;
    }

public String generateToken(
    Map<String, Object> extraClaims,
    UserDetails userdetails
   ){

    return Jwts
    .builder()
    .setClaims(extraClaims)
    .setSubject(userdetails.getUsername())
    .setIssuedAt(new Date(System.currentTimeMillis()))
    .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
    .signWith(getSignInkey(), SignatureAlgorithm.HS256)
    .compact();
   }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){

        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);


    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private java.util.Date extractExpiration(String token) {
        return extractClaim(token, Claims :: getExpiration);
    }

    private Claims extractAllClaims(String token){

        return Jwts
        .parserBuilder()
        .setSigningKey(getSignInkey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }

    private SecretKey getSignInkey() {
        

        Key mykey=new Key();
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        
        return Keys.hmacShaKeyFor(keyBytes);
    }
    

}

package rs.webshop.filter;

import static java.lang.String.format;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rs.webshop.domain.User;

@Component
//@Service
public class JwtTokenUtil {

  //@Value("${security.jwt.secret-key}")
  private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
  private final String jwtIssuer = "example.io";

  @Value("${security.jwt.expiration-time}")
  private long jwtExpiration;

  private final static Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

  public String generateAccessToken(User user) {
    return Jwts.builder()
        .setSubject(format("%s,%s", user.getId(), user.getUsername()))
        .setIssuer(jwtIssuer)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserId(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject().split(",")[0];
  }

  public String getUsername(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject().split(",")[1];
  }

  public long getExpirationDate() {
        /*Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();*/
    return jwtExpiration;
  }

  public boolean validate(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      LOGGER.error("Invalid JWT signature - {}", ex.getMessage());
    } catch (MalformedJwtException ex) {
      LOGGER.error("Invalid JWT token - {}", ex.getMessage());
      LOGGER.error("Token: {}", token);
    } catch (ExpiredJwtException ex) {
      LOGGER.error("Expired JWT token - {}", ex.getMessage());
    } catch (UnsupportedJwtException ex) {
      LOGGER.error("Unsupported JWT token - {}", ex.getMessage());
    } catch (IllegalArgumentException ex) {
      LOGGER.error("JWT claims string is empty - {}", ex.getMessage());
    }
    return false;
  }

}

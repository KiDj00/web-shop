package rs.webshop.filter;

import static java.lang.String.format;
import static java.time.LocalTime.now;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rs.webshop.domain.User;

@Component
//@Service
public class JwtTokenUtil {

  //@Value("${security.jwt.secret-key}")
  private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
  private final String jwtIssuer = "example.io";
  private final String jwtIssuer1 = "example.io1";

  @Value("${security.jwt.expiration-time}")
  private long jwtExpiration;

  @Value("${security.jwt.refresh-expiration-time}")
  private long refreshExpiration;

  private final static Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
  private final TokenBlacklist tokenBlacklist;

  @Autowired
  public JwtTokenUtil(TokenBlacklist tokenBlacklist) {
    this.tokenBlacklist = tokenBlacklist;
  }

  public String generateAccessToken(User user) {
    return Jwts.builder()
        .setSubject(format("%s,%s", user.getId(), user.getUsername()))
        .setIssuer(jwtIssuer)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration)) // 1 h
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String generateRefreshToken(User user) {
    return Jwts.builder()
        .setSubject(format("%s,%s", user.getId(), user.getUsername()))
        .setIssuer(jwtIssuer1)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration)) //2h
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

  public LocalDateTime getExpirationDate() {
    return LocalDateTime.now().plus(jwtExpiration, ChronoUnit.MILLIS);
  }

  public boolean validate(String token) {
    try {
      Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
      if (jwtIssuer1.equals(claims.getIssuer())) {
        throw new UnsupportedJwtException("Refresh token cannot be used to access endpoints");
      }
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

  public boolean validateRefresh(String token) {
    try {
      Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
      if (jwtIssuer.equals(claims.getIssuer())) {
        throw new UnsupportedJwtException("JWT token cannot be used to refresh tokens");
      }
      return false;
    } catch (SignatureException ex) {
      LOGGER.error("Invalid JWT signature - {}", ex.getMessage());
    } catch (MalformedJwtException ex) {
      LOGGER.error("Invalid JWT token - {}", ex.getMessage());
      LOGGER.error("Token: {}", token);
    } catch (ExpiredJwtException ex) {
      LOGGER.error("Expired JWT token - {}", ex.getMessage());
    } catch (UnsupportedJwtException ex) {
      LOGGER.error("Unsupported JWT token - {}", ex.getMessage());
      return true;
    } catch (IllegalArgumentException ex) {
      LOGGER.error("JWT claims string is empty - {}", ex.getMessage());
    }
    return false;
  }

  public boolean validateBlackList(String token) {

    if (tokenBlacklist.isTokenBlacklisted(token)) {
      LOGGER.error("Token is blacklisted");
      return false;
    }
    return true;
  }

}

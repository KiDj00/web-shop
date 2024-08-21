package rs.webshop.filter;

import static org.springframework.util.StringUtils.isEmpty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.webshop.dao.UserDAO;
import rs.webshop.domain.Role;
import rs.webshop.domain.User;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

  private final JwtTokenUtil jwtTokenUtil;
  private final UserDAO userDAO;
  private final static Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);


  public JwtTokenFilter(JwtTokenUtil jwtTokenUtil,
      UserDAO userDAO) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.userDAO = userDAO;
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    boolean isRegisterEndpoint = "/auth/register".equals(requestURI);

    if (isRegisterEndpoint) {
      chain.doFilter(request, response);
      return;
    }

    // Get authorization header and validate
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (isEmpty(header) || !header.startsWith("Bearer ")) {
      LOGGER.error("Authorization header is missing or does not start with Bearer");
      LOGGER.error("HEADER: " + header);
      chain.doFilter(request, response);
      return;
    }

    // Get jwt token and validate
    final String token = header.split(" ")[1].trim();
    LOGGER.info("Extracted token: {}", token); // Log extracted token

    if (!jwtTokenUtil.validateBlackList(token)) {
      LOGGER.error("Token is blacklisted");
      chain.doFilter(request, response);
      return;
    }

    boolean isRefreshTokenEndpoint = "/auth/refresh-token".equals(requestURI);

    if (isRefreshTokenEndpoint) {
      if (jwtTokenUtil.validateRefresh(token)) {
        LOGGER.error("Refresh token validation failed");
        chain.doFilter(request, response);
        return;
      }
    } else {
      if (!jwtTokenUtil.validate(token)) {
        LOGGER.error("Token validation failed");
        chain.doFilter(request, response);
        return;
      }
    }

    // Get user identity and set it on the spring security context
    Optional<User> user = userDAO.findByUsername(jwtTokenUtil.getUsername(token));

    if (user.isPresent()) {
      Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
      for (Role role : user.get().getRoles()) {
        authorities.add(new SimpleGrantedAuthority(role.getName().name()));
      }

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          user.get(), null, authorities);

      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      LOGGER.info("User authenticated: {}", user.get().getUsername());
      LOGGER.info("User roles: {}", authorities.stream().map(SimpleGrantedAuthority::getAuthority)
          .collect(Collectors.toList()));
    }

    chain.doFilter(request, response);
  }
}

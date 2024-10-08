package rs.webshop.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.webshop.domain.User;
import rs.webshop.dto.user.LoginUserDto;
import rs.webshop.dto.user.RegisterUserDto;
import rs.webshop.exception.DAOException;
import rs.webshop.filter.JwtTokenUtil;
import rs.webshop.login.LoginResponse;
import rs.webshop.service.AuthenticationService;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

  private final JwtTokenUtil jwtTokenUtil;
  private final AuthenticationService authenticationService;

  public AuthenticationController(JwtTokenUtil jwtTokenUtil,
      AuthenticationService authenticationService) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.authenticationService = authenticationService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
    User authenticatedUser = authenticationService.authenticate(loginUserDto);

    String jwtToken = jwtTokenUtil.generateAccessToken(authenticatedUser);
    String refreshToken = jwtTokenUtil.generateRefreshToken(authenticatedUser);

    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken(jwtToken);
    loginResponse.setRefreshToken(refreshToken);
    loginResponse.setExpiresIn(jwtTokenUtil.getExpirationDate());

    return ResponseEntity.ok(loginResponse);
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto)
      throws DAOException {
    User registeredUser = authenticationService.signup(registerUserDto);

    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping("/logout")
  public void logout(@RequestHeader("Authorization") String token, HttpServletResponse response) {
    if (token != null && token.startsWith("Bearer ")) {
      token = token.substring(7);
    }

    // Add the token to the blacklist
    authenticationService.logout(token);

    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
  }


  @PostMapping("/refreshToken")
  public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String token) {
    if (token != null && token.startsWith("Bearer ")) {
      token = token.substring(7);
    }

    if (jwtTokenUtil.validateRefresh(token)) {
      return ResponseEntity.status(401).build();
    }
    String username = jwtTokenUtil.getUsername(token);
    User user = authenticationService.findByUsername(username);
    String newToken = jwtTokenUtil.generateAccessToken(user);

    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken(newToken);
    loginResponse.setRefreshToken(token);
    loginResponse.setExpiresIn(jwtTokenUtil.getExpirationDate());
    return ResponseEntity.ok(loginResponse);
  }

}

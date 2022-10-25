package com.zemelya.security.jwt;

import com.zemelya.configuration.JwtSecurityConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.jsonwebtoken.Claims.SUBJECT;
import static java.util.Calendar.MILLISECOND;

@Component
@RequiredArgsConstructor
public class JwtTokenHelper {

  public static final String CREATE_VALUE = "created";

  public static final String ROLES = "roles";

  public static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;

  public static final String JWT = "JWT";

  private final JwtSecurityConfig jwtTokenConfig;

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(SUBJECT, userDetails.getUsername());
    claims.put(CREATE_VALUE, generateCurrentDate());
    claims.put(ROLES, getEncryptedRoles(userDetails));
    return generateToken(claims);
  }

  private String generateToken(Map<String, Object> claims) {

    return Jwts.builder()
        .setHeader(generateJWTHeaders())
        .setClaims(claims)
        .setExpiration(generateExpirationDate())
        .signWith(ALGORITHM, jwtTokenConfig.getSecret())
        .compact();
  }

  private Map<String, Object> generateJWTHeaders() {
    Map<String, Object> jwtHeaders = new LinkedHashMap<>();
    jwtHeaders.put("typ", JWT);
    jwtHeaders.put("alg", ALGORITHM.getValue());

    return jwtHeaders;
  }

  public String getUsernameFromToken(String token) {
    return getClaimsFromToken(token).getSubject();
  }

  private Claims getClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(jwtTokenConfig.getSecret()).parseClaimsJws(token).getBody();
  }

  private Date generateCurrentDate() {
    return new Date();
  }

  private Date generateExpirationDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(MILLISECOND, jwtTokenConfig.getExpiration());
    return calendar.getTime();
  }

  private List<String> getEncryptedRoles(UserDetails userDetails) {
    return userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .map(s -> s.replace("ROLE_", ""))
        .map(String::toLowerCase)
        .collect(Collectors.toList());
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return username.equals(userDetails.getUsername());
  }
}

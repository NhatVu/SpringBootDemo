//package com.example.demo.filters;
//
//import com.example.demo.domain.Role;
//import com.example.demo.helper.Utils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.util.JSONPObject;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
//
//    private final AuthenticationManager authenticationManager;
//
//    public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
//        logger.info("call CustomAuthenticationFilter");
//        this.authenticationManager = authenticationManager;
//    }
//
//    // path /login is handled by Spring Security and will call this method.
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
////        String email = request.getParameter("email");
////        String password = request.getParameter("password");
//        try {
//            String requestData = request.getReader().lines().collect(Collectors.joining());
//            JSONObject json = new JSONObject(requestData);
//            String email = json.getString("email");
//            String password = json.getString("password");
//
//            logger.info("email: {}, password: {}", email, password);
//
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
//            return authenticationManager.authenticate(token);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        User user = (User) authResult.getPrincipal();
//        com.example.demo.domain.User customUser = new com.example.demo.domain.User();
//        customUser.setEmail(user.getUsername());
//        List<Role> roles = new ArrayList<>();
//        user.getAuthorities().forEach(e -> roles.add(new Role(null, e.getAuthority())));
//        customUser.setRoles(roles);
//
//        String access_token = Utils.generateJWTToken(customUser).get("token");
//        Map<String, String> res = new HashMap<>();
//        res.put("access_token", access_token);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//        new ObjectMapper().writeValue(response.getOutputStream(), res);
//    }
//}

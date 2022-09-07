package com.example.demo.resources;

import com.example.demo.domain.User;
import com.example.demo.helper.APIResponseUtils;
import com.example.demo.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Constants;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    UserService userService;
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllUser(){
        List<User> users = userService.getAllUser();
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), users);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);
        if (user == null){
            Map<String, String> err = new HashMap<>();
            err.put("content", "wrong email + password");
            return new ResponseEntity<>(APIResponseUtils.buildAPIError(HttpStatus.NOT_FOUND.value(), err), HttpStatus.FORBIDDEN);
        }
        Map<String, String> token = generateJWTToken(user);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), token);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody Map<String, Object> userMap){
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.registerUser(firstName, lastName, email, password);
        Map<String, String> token = generateJWTToken(user);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), token);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

}

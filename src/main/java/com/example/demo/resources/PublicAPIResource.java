package com.example.demo.resources;

import com.example.demo.domain.User;
import com.example.demo.helper.APIResponseUtils;
import com.example.demo.helper.Utils;
import com.example.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class PublicAPIResource {
    Logger logger = LoggerFactory.getLogger(PublicAPIResource.class);
    @Autowired
    UserService userService;
    @GetMapping()
    public ResponseEntity<Map<String, Object>> testPublicAPI(){
        Map<String, Object> res = new HashMap<>();
        res.put("message", "This is public api. Allow all user without authentication");
        return new ResponseEntity<>(APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), res), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, Object> userMap) {
        logger.info("call /api/users/login");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);
        if (user == null){
            Map<String, String> err = new HashMap<>();
            err.put("content", "wrong email + password");
            return new ResponseEntity<>(APIResponseUtils.buildAPIError(HttpStatus.NOT_FOUND.value(), err), HttpStatus.FORBIDDEN);
        }
        Map<String, String> token = Utils.generateJWTToken(user);
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
        Map<String, String> token = Utils.generateJWTToken(user);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), token);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

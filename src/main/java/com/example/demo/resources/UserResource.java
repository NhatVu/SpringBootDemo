package com.example.demo.resources;

import com.example.demo.domain.User;
import com.example.demo.helper.APIResponseUtils;
import com.example.demo.helper.Utils;
import com.example.demo.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Constants;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Validated
@RolesAllowed({Constants.ROLE_USER, Constants.ROLE_ADMIN})
public class UserResource {
    Logger logger = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    UserService userService;
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllUser(@RequestParam(name = "offset", defaultValue = "0") int page, @RequestParam(defaultValue = "3") int pageSize){
        List<User> users = userService.getAllUser(page, pageSize);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), users);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findUserById(@PathVariable @Min(2) int id){
        User user = userService.findById(id);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), user);
        return new ResponseEntity<>(res, HttpStatus.OK);
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



    @PostMapping("/addrole")
    public ResponseEntity<Map<String, Object>> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getEmail(), form.getRoleName());
        return new ResponseEntity<>(APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), "add success"), HttpStatus.OK);
    }

    @Data
    class RoleToUserForm{
        private String email;
        private String roleName;
    }



}

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

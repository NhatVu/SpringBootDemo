package com.example.demo.resources;

import com.example.demo.Constants;
import com.example.demo.helper.APIResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admins")
@Validated
@RolesAllowed(Constants.ROLE_ADMIN)
public class AdminResource {

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> testAdminRole(){
        Map<String, Object> res = new HashMap<>();
        res.put("message", "Success! This path only accepts admin role");
        return new ResponseEntity<>(APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), res), HttpStatus.OK);
    }
}

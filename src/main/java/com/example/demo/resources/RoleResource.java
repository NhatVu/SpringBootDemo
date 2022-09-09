package com.example.demo.resources;

import com.example.demo.domain.Role;
import com.example.demo.helper.APIResponseUtils;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
@Validated
public class RoleResource {
    @Autowired
    UserService userService;
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllRoles(){
        List<Role> allRoles  = userService.getALlRoles();
        return new ResponseEntity<>(APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), allRoles), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> saveRoles(@RequestBody Role role){
        Role roleRes = userService.saveRole(role);
        return new ResponseEntity<>(APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), roleRes), HttpStatus.OK);
    }
}

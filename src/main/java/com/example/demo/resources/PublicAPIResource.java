package com.example.demo.resources;

import com.example.demo.helper.APIResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class PublicAPIResource {
    @GetMapping()
    public ResponseEntity<Map<String, Object>> testPublicAPI(){
        Map<String, Object> res = new HashMap<>();
        res.put("message", "This is public api. Allow all user without authentication");
        return new ResponseEntity<>(APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), res), HttpStatus.OK);
    }
}

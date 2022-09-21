package com.example.demo.helper;

import com.example.demo.Constants;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .claim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.joining(",")))
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    public static Map<String, Object> verifyToken(String token){
        Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                .parseClaimsJws(token).getBody();
        Map<String, Object> res = new HashMap<>();
        int userId = Integer.parseInt(claims.get("userId").toString());
        String[] roles = claims.get("roles").toString().split(",");

        res.put("userId", userId);
        res.put("roles", roles);
        return res;
    }

    public static Map<String, Object> parseJSON(String jsonStr) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // ignore all null fields globally
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Map<String, Object> map = mapper.readValue(jsonStr, Map.class);
        return map;
    }

    public static void main(String[] args) throws JsonProcessingException {
        String jsonStr = "{\n" +
                "    \"data\": {\n" +
                "        \"token\": \"ey2iM\"\n" +
                "    },\n" +
                "    \"status\": 200\n" +
                "}";
        Map<String, Object> map = parseJSON(jsonStr);
        System.out.println("map: " + map);
        System.out.println("map key: " + ((Map)map.get("data")).get("token"));

    }
}


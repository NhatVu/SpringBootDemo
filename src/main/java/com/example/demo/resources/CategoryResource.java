package com.example.demo.resources;

import com.example.demo.domain.Category;
import com.example.demo.helper.APIResponseUtils;
import com.example.demo.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {
    Logger logger = LoggerFactory.getLogger(CategoryResource.class);
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllCategories(HttpServletRequest request){
        logger.info("get all category call");
        logger.debug("get all category call");
        int userId = (Integer) request.getAttribute("userId");
        List<Category> categories = categoryService.fetchAllCategories(userId);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), categories);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Map<String, Object>> getCategoryById(HttpServletRequest request,
                                                    @PathVariable("categoryId") Integer categoryId) {
        int userId = (Integer) request.getAttribute("userId");
        Category category = categoryService.fetchCategoryById(userId, categoryId);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), category);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> addCategory(HttpServletRequest request,
                                                @RequestBody Map<String, Object> categoryMap) {
        int userId = (Integer) request.getAttribute("userId");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        Category category = categoryService.addCategory(userId, title, description);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), category);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String, Object>> updateCategory(HttpServletRequest request,
                                                               @PathVariable("categoryId") Integer categoryId,
                                                               @RequestBody Category category) {
        int userId = (Integer) request.getAttribute("userId");
        categoryService.updateCategory(userId, categoryId, category);
        Map<String, Object> map = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), "update success");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}

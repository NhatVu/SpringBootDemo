package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;
    private Integer userId;
    private String title;
    private String description;
    private Double totalExpense;

//    public Category(Integer categoryId, Integer userId, String title, String description, Double totalExpense) {
//        this.categoryId = categoryId;
//        this.userId = userId;
//        this.title = title;
//        this.description = description;
//        this.totalExpense = totalExpense;
//    }

//    public Integer getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Integer categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Double getTotalExpense() {
//        return totalExpense;
//    }
//
//    public void setTotalExpense(Double totalExpense) {
//        this.totalExpense = totalExpense;
//    }
}

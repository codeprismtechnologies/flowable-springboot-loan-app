package com.mediumBlog.Flowabledemo.serviceImpl;

import lombok.Data;

@Data
public class LoanRequest {
    long id;
    private String country;
    int age;
    int amount;

}

package org.employee_service.model;

import lombok.Data;

@Data
public class LoanRequestDTO {
    private int id;
    private String country;
    private int age;
    private int amount;

}
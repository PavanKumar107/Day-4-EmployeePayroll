package com.bl.employeepayrollapp.dto;
import lombok.Data;

@Data
public class EmployeeDTO{
    private String firstName;
    private String lastName;
    private String companyName;
    private String mobileNum;
    private long salary;
    private String department;
    private String emailId;
    private String password;
}

package com.bl.employeepayrollapp.model;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.bl.employeepayrollapp.dto.EmployeeDTO;

import lombok.Data;

@Entity
@Table(name = "Employee")
@Data
public class EmployeeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId;
	private String firstName;
	private String lastName;
	private String companyName;
	private String mobileNum;
	private long salary;
	private String department;
	private String emailId;
	private String password;
    private LocalDateTime registeredDate;
    private LocalDateTime updatedDate;

	public EmployeeModel(EmployeeDTO employeeDto) {
		this.firstName = employeeDto.getFirstName();
		this.lastName = employeeDto.getLastName();
		this.companyName = employeeDto.getCompanyName();
		this.mobileNum = employeeDto.getMobileNum();
		this.salary = employeeDto.getSalary();
		this.department = employeeDto.getDepartment();
		this.emailId=employeeDto.getEmailId();
        this.password=employeeDto.getPassword();
	}

	public EmployeeModel() {

	}
}


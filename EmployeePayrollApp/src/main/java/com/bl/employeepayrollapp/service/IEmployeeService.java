package com.bl.employeepayrollapp.service;

import java.util.List;

import com.bl.employeepayrollapp.dto.EmployeeDTO;
import com.bl.employeepayrollapp.model.EmployeeModel;
import com.bl.employeepayrollapp.util.Response;

public interface IEmployeeService {
	EmployeeModel addemployee(EmployeeDTO employeeDTO);

	EmployeeModel updateemployee( EmployeeDTO employeeDTO,long id);
	
	 List<EmployeeModel> getEmpData(String token);

    EmployeeModel deleteEmployee(Long id);
    
    Response login(String email, String password);
}

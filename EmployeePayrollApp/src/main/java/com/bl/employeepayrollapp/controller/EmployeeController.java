package com.bl.employeepayrollapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bl.employeepayrollapp.dto.EmployeeDTO;
import com.bl.employeepayrollapp.model.EmployeeModel;
import com.bl.employeepayrollapp.service.IEmployeeService;
import com.bl.employeepayrollapp.util.Response;

@RestController
@RequestMapping("/employeepayroll")
public class EmployeeController {
	@Autowired
    IEmployeeService employeeService;
	
    @PostMapping("/addemployee")
    public EmployeeModel addemployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.addemployee(employeeDTO);
    }
    
    @PutMapping("/updateemployee/{id}")
    public EmployeeModel updateemployee(@RequestBody EmployeeDTO employeeDTO,@PathVariable long id){
        return employeeService.updateemployee(employeeDTO,id);
    }
    
    @GetMapping("/getEmployeedata")
    public List<EmployeeModel> getallemployee(@RequestHeader String token){
        return employeeService.getEmpData(token);
    }

    @DeleteMapping("/deleteemployee/{id}")
    public EmployeeModel deleteemployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }
    
    @PostMapping("login")
    public Response login(@RequestParam String email,@RequestParam String password){
        return employeeService.login(email,password);
    }

}


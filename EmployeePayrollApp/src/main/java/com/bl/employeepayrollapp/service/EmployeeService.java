package com.bl.employeepayrollapp.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bl.employeepayrollapp.dto.EmployeeDTO;
import com.bl.employeepayrollapp.exception.EmployeeNotFoundException;
import com.bl.employeepayrollapp.model.EmployeeModel;
import com.bl.employeepayrollapp.repository.EmployeeRepository;
import com.bl.employeepayrollapp.util.Response;
import com.bl.employeepayrollapp.util.TokenUtil;

@Service
public class EmployeeService implements IEmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	 @Autowired
	    TokenUtil tokenUtil;


	@Override
	public EmployeeModel addemployee(EmployeeDTO employeeDTO) {
		EmployeeModel employeeModel = new EmployeeModel(employeeDTO);
		employeeModel.setRegisteredDate(LocalDateTime.now());
		employeeRepository.save(employeeModel);
		return employeeModel;
	}

	@Override
	public EmployeeModel updateemployee(EmployeeDTO employeeDTO, long id) {
		Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(id);
		if (isEmployeePresent.isPresent()) {
			isEmployeePresent.get().setFirstName(employeeDTO.getFirstName());
			isEmployeePresent.get().setLastName(employeeDTO.getLastName());
			isEmployeePresent.get().setMobileNum(employeeDTO.getMobileNum());
			isEmployeePresent.get().setSalary(employeeDTO.getSalary());
			isEmployeePresent.get().setDepartment(employeeDTO.getDepartment());
			isEmployeePresent.get().setCompanyName(employeeDTO.getCompanyName());
			isEmployeePresent.get().setUpdatedDate(LocalDateTime.now());
			return isEmployeePresent.get();
		}
		throw new EmployeeNotFoundException(400,"Employee not present");
	}

	@Override
	  public List<EmployeeModel> getEmpData(String token) {
        Long empId=tokenUtil.decodeToken(token);
        Optional<EmployeeModel> isEmployeePresent=employeeRepository.findById(empId);
        if(isEmployeePresent.isPresent()) {
            List<EmployeeModel> getallemployee = employeeRepository.findAll();
            if (getallemployee.size() > 0)
                return getallemployee;
            else
                throw new EmployeeNotFoundException(400, "No DATA Present");
        }
        throw new EmployeeNotFoundException(400,"Employee Not found");
    }

	@Override
	public EmployeeModel deleteEmployee(Long id) {
		Optional<EmployeeModel> isEmployeePresent=employeeRepository.findById(id);
		if(isEmployeePresent.isPresent()){
			employeeRepository.delete(isEmployeePresent.get());
			return isEmployeePresent.get();
		}
		throw new EmployeeNotFoundException(400,"Employee Not Present");
	}
	
	  @Override
	    public Response login(String email, String password) {
	        Optional<EmployeeModel> isEmailPresent=employeeRepository.findByEmailId(email);
	        if(isEmailPresent.isPresent()){
	            if(isEmailPresent.get().getPassword().equals(password)){
	                String token=tokenUtil.createToken(isEmailPresent.get().getEmployeeId());
	                return new Response("login succesfull",200,token);
	            }
	            throw new EmployeeNotFoundException(400,"Invald credentials");
	        }
	        throw new EmployeeNotFoundException(400,"Employee not found");
	    }
}

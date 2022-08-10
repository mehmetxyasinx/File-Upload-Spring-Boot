package com.db.UserService.impl;

import java.util.List;


import com.db.UserEntity.Employee;
import com.db.UserException.ResourceNotFoundException;
import com.db.UserRepository.EmployeeRepository;
import org.springframework.stereotype.Service;


import com.db.UserService.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {

		return employeeRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("User", "Id", id));
		
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		

		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id)); 
		
		existingEmployee.setUsername(employee.getUsername());
		existingEmployee.setPassword(employee.getPassword());

		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		

		employeeRepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("Employee", "Id", id));
		employeeRepository.deleteById(id);
	}
	
}

package com.service;

import java.util.List;

import com.dto.SendDataDTO;
import com.exceptions.DepartmentException;
import com.exceptions.EmployeeException;
import com.models.Employee;

public interface EmployeeService {
	
	public String addEmployee(List<Employee> list) throws EmployeeException,DepartmentException;
	
	public SendDataDTO getEmployee(String date) throws EmployeeException;	
}

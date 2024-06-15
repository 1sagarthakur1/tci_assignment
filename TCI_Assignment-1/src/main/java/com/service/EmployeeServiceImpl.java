package com.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.DataDTO;
import com.dto.EmployeeDTO;
import com.dto.SendDataDTO;
import com.exceptions.DepartmentException;
import com.exceptions.EmployeeException;
import com.models.Currency;
import com.models.Department;
import com.models.Employee;
import com.repository.DepartmentRepository;
import com.repository.EmployeeRepository;

import jakarta.websocket.DeploymentException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public String addEmployee(List<Employee> list) throws EmployeeException, DepartmentException {
//		System.out.println(departments.get().getDpartmentName());
		for(int i=0;i<list.size();i++) {
			    try {
			    	Optional<Department> departments = departmentRepository.findById(list.get(i).getDepartment().getDpartmentName());

			    	try {
			    		employeeRepository.save(list.get(i));		
			    		
			    	} catch (Exception e) {
			    		throw new EmployeeException(e.getMessage());
			    	}
		
				} catch (Exception e) {
					throw new DepartmentException("department not exist");
				}
		}
		return "Employee added";
	}
	
	
	public SendDataDTO getEmployee(String date) throws EmployeeException{
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
	                .parseCaseInsensitive()
	                .appendPattern("MMM-dd-yyyy")
	                .toFormatter(Locale.ENGLISH);
		
		try {
			LocalDate.parse(date, formatter);
		} catch (Exception e2) {
			throw new EmployeeException("This is not write formate of date");
		}
		
		List<Employee> employee = employeeRepository.findAll();

		
		List<EmployeeDTO> filteredEmployeesINR = employee.stream()
				    .filter(e -> e.getCurrency() == Currency.INR)
				    .filter(e -> (LocalDate.parse(date, formatter).isAfter(LocalDate.parse(e.getJoiningDate(), formatter)) && LocalDate.parse(date, formatter).isBefore(LocalDate.parse(e.getExitDate(), formatter)) || LocalDate.parse(date, formatter).isEqual(LocalDate.parse(e.getJoiningDate(), formatter)) || LocalDate.parse(date, formatter).isEqual(LocalDate.parse(e.getExitDate(), formatter))))
	                .map(e ->  new EmployeeDTO(e.getEmpName(), e.getAmount()))
	                .collect(Collectors.toList());
		
		List<EmployeeDTO> filteredEmployeesUsd = employee.stream()
			    .filter(e -> e.getCurrency() == Currency.USD)
			    .filter(e -> (LocalDate.parse(date, formatter).isAfter(LocalDate.parse(e.getJoiningDate(), formatter)) && LocalDate.parse(date, formatter).isBefore(LocalDate.parse(e.getExitDate(), formatter)) || LocalDate.parse(date, formatter).isEqual(LocalDate.parse(e.getJoiningDate(), formatter)) || LocalDate.parse(date, formatter).isEqual(LocalDate.parse(e.getExitDate(), formatter))))
                .map(e ->  new EmployeeDTO(e.getEmpName(), e.getAmount()))
                .collect(Collectors.toList());
		
		String string = "";
		if(filteredEmployeesINR.size()==0) {
			string = "There is no any INR amount";
		}else if(filteredEmployeesUsd.size() == 0) {
			string = "There is no any USD amount";
		}
		
		List<DataDTO> list = new ArrayList<>();
		list.add(new DataDTO("INR", filteredEmployeesINR));
		list.add(new DataDTO("USD", filteredEmployeesUsd));
		
		SendDataDTO sandDataDTO = new SendDataDTO(string,list);
		
		return sandDataDTO;
	}
}

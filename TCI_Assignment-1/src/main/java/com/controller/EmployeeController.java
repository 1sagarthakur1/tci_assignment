package com.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.SendDataDTO;
import com.exceptions.EmployeeException;
import com.models.Employee;
import com.service.EmployeeService;
import com.service.Message;

import jakarta.validation.Valid;
import jakarta.websocket.DeploymentException;

@RestController
@RequestMapping(value = "/tci")
public class EmployeeController {
	
	@Autowired
	private  EmployeeService employeeService;
	
	@PostMapping("/employee_bonus")
	public ResponseEntity<Message> addEmployye(@Valid @RequestBody List<Employee> list) throws EmployeeException, DeploymentException {
		
		Message message = new Message();
		message.setLocalDateTime(LocalDateTime.now());
		message.setMessage(employeeService.addEmployee(list));
		return new ResponseEntity<>(message, HttpStatus.OK);
		
	}
	
	@GetMapping("/employee_bonus")
	public ResponseEntity<SendDataDTO> getEmployee(@RequestParam(name = "date") String date){
		SendDataDTO sendDataDTO = employeeService.getEmployee(date);
		return new ResponseEntity<>(sendDataDTO, HttpStatus.OK);
	}
}

package com.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	
	@NotNull(message = "Name id requid")
	@Size(min = 2, max = 25, message = "Employee name size is not good")
	private String empName; 
	
	private int amount;
}

package com.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Employee {
	
	@Id
	@JsonProperty(access = Access.READ_ONLY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userGenerator")
    @SequenceGenerator(name = "userGenerator",sequenceName = "usergen",allocationSize = 1,initialValue = 1)
	private int id;
	
	@NotNull(message = "Name id requid")
	@Size(min = 2, max = 25, message = "Employee name size is not good")
	private String empName;
	
	@ManyToOne
	private Department department;
	
	private int amount;

	@Enumerated(EnumType.STRING)
	private Currency currency;
	
//	@NotNull(message = "Joining date is requid")
	private String joiningDate;
	
//	@NotNull(message = "Exit date is requid")
	private String exitDate;
	
}

# TCI Digtial Labs Assignment

 **REST API SERVICE**
I have created a small Spring Boot project which has two APIS: (a) a POST API to store employee data and (b) a GET API to return those employees who are eligible to receive a bonus on a given date. 

The API's Services  will be used by the **Two** entities:

- **Employee**

- **Department**
  
## Tech Stack

- JAVA
- SPRINGBOOT
- HIBERNATE
- JPA
- MAVEN
- MYSQL

## Dependencies

- SPRING DATA JPA
- SPRING BOOT DEVTOOLS
- SPRING WEB
- MYSQL DRIVER
- VALIDATION
- LOMBOK

## Modules
-EMPLOYEE
-DEPARTMENT


## Feature
- Employee
  -add employees in the department in bulk.
  -get employee that returns the list of employees that are eligible to receive a bonus on a given date.

## REST API
-POST /tci/employee-bonus
POST API that sends a list of employees in its payload. This API will store the employee data in two tables : department and employee.

-GET /tci//employee-bonus?date=”may-27-2022”
GET API that returns the list of employees that are eligible to receive a bonus on a given date.




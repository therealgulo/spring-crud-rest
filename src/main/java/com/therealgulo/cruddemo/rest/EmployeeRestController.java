package com.therealgulo.cruddemo.rest;

import com.therealgulo.cruddemo.entity.Employee;
import com.therealgulo.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Also to make this a candidate for component scanning.
@RequestMapping("/api")

public class EmployeeRestController {

    private EmployeeService employeeService;

    //inject the EmployeeService
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //expose "/employees" and return a list of employees.
    @GetMapping("/employees")
    public List<Employee>findAll(){
        return employeeService.findAll();
    }
    //expose "/employees/{employeeId}" and return an employee.
    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee with "+ employeeId+ " id not found");
        }
        return theEmployee;
    }
    //expose "/employees" and add a new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //incase they pass in an id in Json. set the id to 0
        //this is to force a save of a new employee instead of update
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        //updated employee.
        return dbEmployee;
    }
    //add mapping for PUT "/employees" and update an existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    //add mapping for DELETE "/employees/{employeeId}" - delete an employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null){
            throw new RuntimeException("Employee with id "+ employeeId + " does not exist.");
        }
        employeeService.deleteById(employeeId);
        return "Employee with id "+ employeeId + " deleted.";
    }
}

package com.therealgulo.cruddemo.service;


import com.therealgulo.cruddemo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int theId);
    Employee save(Employee employee);
    void deleteById(int theId);

}

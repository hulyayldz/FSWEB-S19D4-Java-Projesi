package com.workintech.s19d4.controller;

import com.workintech.s19d4.entity.Employee;
import com.workintech.s19d4.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;


    @GetMapping
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee find(@PathVariable Long id){
        return employeeService.findById(id);
    }
    @GetMapping("/byEmail/{email}")
    public Employee find(@PathVariable String email){
        return employeeService.findByEmail(email);
    }

    @GetMapping("/byOrder")
    public List<Employee> findByOrder(){
        return employeeService.findByOrder();
    }

    @GetMapping("/bySalary/{salary}")
    public List<Employee> findBySalary(@PathVariable Double salary){
        return employeeService.findBySalary(salary);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("/{id}")
    public Employee delete(@RequestBody Long id){
        return employeeService.delete(id);
    }
}

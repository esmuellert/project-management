package com.jrp.pma.api.controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
    final
    EmployeeRepository employeeRepository;


    public EmployeeApiController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return employeeRepository.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody @Validated Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody @Validated Employee employee) {
        return employeeRepository.save(employee);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Employee partialUpdate(@PathVariable("id") long id, @RequestBody @Validated Employee employee) {
        Employee currEmployee = employeeRepository.findById(id).get();
        if (employee.getEmail() != null) {
            currEmployee.setEmail(employee.getEmail());
        }
        if (employee.getFirstName() != null) {
            currEmployee.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null) {
            currEmployee.setLastName(employee.getLastName());
        }
        return employeeRepository.save(currEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignored) {

        }
    }

    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginateEmployees(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }
}

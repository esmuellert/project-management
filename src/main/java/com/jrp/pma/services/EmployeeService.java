package com.jrp.pma.services;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<EmployeeProject> employeeProjectCount() {
        return employeeRepository.employeeProjects();
    }

    public void saveEmployeeToDatabase(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignored) {

        }
    }

    public Employee findEmployeeById(long id) {
        return employeeRepository.findByEmployeeId(id);
    }

}

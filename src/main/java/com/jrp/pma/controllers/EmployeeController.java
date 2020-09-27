package com.jrp.pma.controllers;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


    private final ProjectService projectService;

    private final EmployeeService employeeService;

    public EmployeeController(ProjectService projectService,
                              EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String displayEmployees(Model model) {
        List<Employee> employeeList = employeeService.findAllEmployees();
        model.addAttribute("employees", employeeList);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {
        Employee employee = new Employee();
        List<Project> projectList = projectService.findAllProjects();
        model.addAttribute("employee", employee);
        model.addAttribute("allProjects", projectList);
        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(@Valid Employee employee, Errors errors) {
        if (!errors.hasErrors()) {
            employeeService.saveEmployeeToDatabase(employee);
        }

        return "redirect:/employees/new";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees/";
    }

    @GetMapping("/update")
    public String displayEmployeeUpdateForm(@RequestParam("id") long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("allProjects", projectService.findAllProjects());
        return "employees/new-employee";
    }

}

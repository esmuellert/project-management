package com.jrp.pma.controllers;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final EmployeeService employeeService;

    private final ProjectService projectService;

    public ProjectController(EmployeeService employeeService,
                             ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String displayProjects(Model model) {
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        List<Employee> employees = employeeService.findAllEmployees();
        model.addAttribute("allEmployees", employees);
        Project project = new Project();
        model.addAttribute("project", project);
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project) {
        projectService.saveProjectToDatabase(project);
        return "redirect:/projects/new";
    }

    @GetMapping("/update")
    public String displayUpdateProjectForm(@RequestParam("id") long id, Model model) {
        model.addAttribute("project", projectService.findProjectById(id));
        model.addAttribute("allEmployees", employeeService.findAllEmployees());
        return "projects/new-project";
    }

    @GetMapping("/delete")
    public String deleteProject(@RequestParam("id") long id) {
        projectService.deleteProjectById(id);
        return "redirect:/projects/";
    }
}

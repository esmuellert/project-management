package com.jrp.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Value("version: " + "${version}")
    private String version;

    private final EmployeeService employeeService;


    private final ProjectService projectService;

    public HomeController(EmployeeService employeeService,
                          ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();

        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);

        List<EmployeeProject> employeesProjectCount = employeeService.employeeProjectCount();
        model.addAttribute("employeesProjectCount", employeesProjectCount);

        List<ChartData> projectData = projectService.projectStageCount();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        model.addAttribute("projectStatusCount", jsonString);

        model.addAttribute("versionNumber", version);

        return "main/home";

    }
}

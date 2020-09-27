package com.jrp.pma.services;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public List<ChartData> projectStageCount() {
        return projectRepository.getProjectStatus();
    }

    public void saveProjectToDatabase(Project project) {
        projectRepository.save(project);
    }

    public Project findProjectById(long id) {
        return projectRepository.findByProjectId(id);
    }

    public void deleteProjectById(long id) {
        try {
            projectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignore) {

        }
    }
}

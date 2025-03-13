package com.example.ProjectTaskManagement.Controllers;

import com.example.ProjectTaskManagement.Entities.Project;
import com.example.ProjectTaskManagement.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// This class handles HTTP requests for creating, retrieving, updating, and deleting projects.
@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    // This method creates a new project.
    @PostMapping
    public Project createProject(@RequestBody Project project){
        return projectService.createProject(project);
    }

    // This method retrieves all projects.
    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    // This method retrieves a project by its ID.
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectByProjectId(@PathVariable Long projectId){
        return ResponseEntity.ok(projectService.getProjectByProjectId(projectId));
    }

    // This method updates a project.
    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId,@RequestBody Project updatedProject){
        return ResponseEntity.ok(projectService.updateProject(projectId, updatedProject));
    }

    // This method deletes a project.
    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Long projectId){
        projectService.deleteProject(projectId);
        return new ResponseEntity<String>("Project Deleted successfully", HttpStatus.OK);
    }
}

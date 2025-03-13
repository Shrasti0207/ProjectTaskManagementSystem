package com.example.ProjectTaskManagement.Services;
import com.example.ProjectTaskManagement.Entities.Project;
import com.example.ProjectTaskManagement.Exceptions.DuplicateResourceException;
import com.example.ProjectTaskManagement.Exceptions.ResourceNotFoundException;
import com.example.ProjectTaskManagement.Exceptions.ValidationException;
import com.example.ProjectTaskManagement.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// This class is used to implement the business logic of the Project.
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // This method is used to create a new project.
    public Project createProject(Project project){
        if (projectRepository.existsByName(project.getName())) {
            throw new DuplicateResourceException("project Name already exists: " + project.getName());
        }

        if (project.getName() == null) {
            throw new ValidationException("project Name is required : "+project.getName());
        }
        return projectRepository.save(project);
    }

    // This method is used to get all the projects.
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    // This method is used to get a project by its id.
    public Project getProjectByProjectId(Long projectId){
        Optional<Project> opt = projectRepository.findById(projectId);
        if(opt.isPresent()){
            return opt.get();
        }else{
            throw new ResourceNotFoundException("Id "+projectId+" not found");
        }
    }

    // This method is used to update a project.
    public Project updateProject(Long projectId, Project updatedproject){
        Optional<Project> opt = projectRepository.findById(projectId);
        if(opt.isPresent()){
            Project existingProject = opt.get();
            existingProject.setName(updatedproject.getName());
            existingProject.setDescription(updatedproject.getDescription());
            existingProject.setStartDate(updatedproject.getStartDate());
            existingProject.setEndDate(updatedproject.getEndDate());
            existingProject.setCreatedBy(updatedproject.getCreatedBy());
            return projectRepository.save(existingProject);
        }else{
            throw new ResourceNotFoundException("Id "+projectId+" not found");
        }
    }

    // This method is used to delete a project.
    public void deleteProject(Long projectId){
        projectRepository.deleteById(projectId);
    }
}

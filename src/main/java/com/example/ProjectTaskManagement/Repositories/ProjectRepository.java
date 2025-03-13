package com.example.ProjectTaskManagement.Repositories;
import com.example.ProjectTaskManagement.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for managing Project entities.
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Method to check if a project with the given name exists.
    boolean existsByName(String name);
}

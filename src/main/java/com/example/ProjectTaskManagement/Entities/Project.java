package com.example.ProjectTaskManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

// Represents a Project entity in the project Task Management system.
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "project_id")
    private Long projectId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String createdBy;

    // Represents the user who created the project.
    @ManyToOne
    @JoinColumn(name = "fk_userId")
    @JsonIgnore
    private UserEntity user;

    // Represents the tasks associated with the project.
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_projectId", referencedColumnName = "project_id")
    private List<Task> tasks;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}

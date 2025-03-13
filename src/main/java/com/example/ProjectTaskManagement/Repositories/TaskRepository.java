package com.example.ProjectTaskManagement.Repositories;
import com.example.ProjectTaskManagement.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

// Repository interface for managing task entities.
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTaskByStatus(String status);
    List<Task> findTaskByPriority(String priority);
    List<Task> findTaskByStatusAndPriority(String status, String priority);

    // Add a new method to get tasks by project id.
    @Query(value = "SELECT * FROM TASK WHERE fk_project_id = :projectId", nativeQuery = true)
    List<Task> getTasksByProjectId(@Param("projectId") Long projectId);
}

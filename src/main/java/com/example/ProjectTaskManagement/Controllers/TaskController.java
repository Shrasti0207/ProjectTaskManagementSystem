package com.example.ProjectTaskManagement.Controllers;
import com.example.ProjectTaskManagement.Entities.Task;
import com.example.ProjectTaskManagement.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

// This class handles HTTP requests for creating, retrieving, updating, and deleting tasks.
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // This method creates a new task.
    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    // This method retrieves all tasks.
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    // This method retrieves a task by its ID.
    @GetMapping("{taskId}")
    public ResponseEntity<Task> getTaskByTaskId(@PathVariable Long taskId){
        return ResponseEntity.ok(taskService.getTaskByTaskId(taskId));
    }

    // This method updates a task.
    @PutMapping("{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId,@RequestBody Task updatedTask){
        return ResponseEntity.ok(taskService.updateTask(taskId, updatedTask));
    }

    // This method deletes a task.
    @DeleteMapping("{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return new ResponseEntity<String>("Task Deleted successfully", HttpStatus.OK);
    }

    // This method updates the status of a task.
    @PatchMapping("{taskId}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestBody Map<String, String> requestBody){
        String status = requestBody.get("status");
        return ResponseEntity.ok(taskService.updateTaskStatus(taskId, status));
    }

    // This method retrieves tasks by status and priority.
    @GetMapping("/filter")
    public ResponseEntity<List<Task>> getTaskByStatusAndPriority(@RequestParam(required = false) String status, @RequestParam(required = false) String priority){
        return ResponseEntity.ok(taskService.getTaskByStatusAndPriority(status, priority));
    }

    // This method retrieves tasks by project ID.
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTaskByProjectId(@PathVariable Long projectId){
        return ResponseEntity.ok(taskService.getTaskByProjectId(projectId));
    }
}

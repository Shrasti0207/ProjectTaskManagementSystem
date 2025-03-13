package com.example.ProjectTaskManagement.Services;

import com.example.ProjectTaskManagement.Entities.Task;
import com.example.ProjectTaskManagement.Exceptions.BadRequestException;
import com.example.ProjectTaskManagement.Exceptions.ResourceNotFoundException;
import com.example.ProjectTaskManagement.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// This class is used to implement the business logic of the Task.
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // This method is used to create a new task.
    public Task createTask(Task task){
        if(task.getTitle() == null){
            throw new BadRequestException("Title is required : "+task.getTitle());
        }
        return taskRepository.save(task);
    }

    // This method is used to get all the tasks.
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    // This method is used to get a task by its id.
    public Task getTaskByTaskId(Long taskId){
        Optional<Task> opt = taskRepository.findById(taskId);
        if(opt.isPresent()){
            return opt.get();
        }else{
            throw new ResourceNotFoundException("Id "+taskId+" not found");
        }
    }

    // This method is used to update a task.
    public Task updateTask(Long taskId, Task updatedTask){
        Optional<Task> opt = taskRepository.findById(taskId);
        if(opt.isPresent()){
            Task existingTask = opt.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setDeadline(updatedTask.getDeadline());
            existingTask.setAssignedTo(updatedTask.getAssignedTo());
            return taskRepository.save(existingTask);
        }else{
            throw new ResourceNotFoundException("Id "+taskId+" not found");
        }
    }

    // This method is used to delete a task.
    public void deleteTask(Long taskId){
        taskRepository.deleteById(taskId);
    }

    // This method is used to update the status of a task.
    public Task updateTaskStatus(Long taskId, String status){
        Optional<Task> opt = taskRepository.findById(taskId);
        if(opt.isPresent()){
            Task existingTaskStatus = opt.get();
            existingTaskStatus.setStatus(status);
            return taskRepository.save(existingTaskStatus);
        }else{
            throw new ResourceNotFoundException("Id "+taskId+" not found");
        }
    }

    // This method is used to get tasks by their status and priority.
    public List<Task> getTaskByStatusAndPriority(String status, String priority){
        List<String> validStatuses = Arrays.asList("todo", "inprogress", "completed");
        List<String> validPriorities = Arrays.asList("medium", "high", "low");
        if(!validStatuses.contains(status)){
            throw new BadRequestException("Invalid status found");
        }
        if(!validPriorities.contains(priority)){
            throw new BadRequestException("Invalid priority found");
        }
        if(status == null && priority == null){
            return taskRepository.findAll();
        } else if(status!=null && priority == null){
            return taskRepository.findTaskByStatus(status);
        } else if(status == null){
            return taskRepository.findTaskByPriority(priority);
        }
        else{
            return taskRepository.findTaskByStatusAndPriority(status, priority);
        }
    }

    // This method is used to get tasks by their project id.
    public List<Task> getTaskByProjectId(Long projectId){
        return taskRepository.getTasksByProjectId(projectId);
    }
}

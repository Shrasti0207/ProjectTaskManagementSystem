package com.example.ProjectTaskManagement.Controllers;
import com.example.ProjectTaskManagement.Entities.UserEntity;
import com.example.ProjectTaskManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// This class handles HTTP requests for creating, retrieving, updating, and deleting users.
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // This method creates a new user.
    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user){
        return userService.createUser(user);
    }

    // This method retrieves all users.
    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    // This method retrieves a user by their ID.
    @GetMapping("{id}")
    public ResponseEntity<UserEntity> getUserByUserId(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserByUserId(id));
    }

    // This method updates a user.
    @PutMapping("{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity updatedUser){
        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }

    // This method deletes a user.
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<String>("User Deleted successfully", HttpStatus.OK);
    }
}

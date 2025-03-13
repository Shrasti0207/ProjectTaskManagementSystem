package com.example.ProjectTaskManagement.Repositories;

import com.example.ProjectTaskManagement.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//Repository interface for managing User entities.
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //Method to check if a user with the given email exists.
    boolean existsByEmail(String email);
}

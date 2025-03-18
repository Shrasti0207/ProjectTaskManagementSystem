package com.example.ProjectTaskManagement.Services;
import com.example.ProjectTaskManagement.Entities.UserEntity;
import com.example.ProjectTaskManagement.Exceptions.DuplicateResourceException;
import com.example.ProjectTaskManagement.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity(123456789L, "John Doe", "john.doe@example.com", "admin",true);
    }

    @Test
    public void createUser_returnSavedUser(){
        given(userRepository.save(userEntity)).willReturn(userEntity);
        UserEntity user = userService.createUser(userEntity);
        assertThat(user).isNotNull();
    }

    @Test
    public void createUser_throwDuplicateException(){
        given(userRepository.existsByEmail(userEntity.getEmail())).willReturn(true);
        try {
            userService.createUser(userEntity);
        } catch (DuplicateResourceException exception) {
            assertEquals("Email already exists: "+ userEntity.getEmail(), exception.getMessage());
        }
    }

    @Test
    public void createUser_throwValidationException(){
        userEntity.setEmail("john.doeexample.com");
        try {
            userService.createUser(userEntity);
        } catch (Exception exception) {
            assertEquals("Invalid email format: "+ userEntity.getEmail(), exception.getMessage());
        }
    }

    @Test
    public void getAllUsers_returnAllUsers(){
        given(userRepository.findAll()).willReturn(List.of(userEntity));
        List<UserEntity> users = userService.getAllUsers();
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    public void getUserByUserId_returnUser(){
        given(userRepository.findById(userEntity.getUserId())).willReturn(Optional.of(userEntity));
        UserEntity user = userService.getUserByUserId(userEntity.getUserId());
        assertThat(user).isNotNull();
        assertThat(user.getUserId()).isEqualTo(userEntity.getUserId());
    }

    @Test
    public void getUserByUserId_throwResourceNotFoundException(){
        given(userRepository.findById(userEntity.getUserId())).willReturn(Optional.empty());
        try {
            userService.getUserByUserId(userEntity.getUserId());
        } catch (Exception exception) {
            assertEquals("Id "+userEntity.getUserId()+" not found", exception.getMessage());
        }
    }

    @Test
    public void updateUser_returnUpdatedUser(){
        given(userRepository.findById(userEntity.getUserId())).willReturn(Optional.of(userEntity));
        userEntity.setUsername("Jane Doe");
        given(userRepository.save(userEntity)).willReturn(userEntity);
        UserEntity updatedUser = userService.updateUser(userEntity.getUserId(), userEntity);
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getUsername()).isEqualTo(userEntity.getUsername());
    }

    @Test
    public void deleteUser_returnDeletedMessage(){
        willDoNothing().given(userRepository).deleteById(userEntity.getUserId());
        userService.deleteUser(userEntity.getUserId());
        verify(userRepository, times(1)).deleteById(userEntity.getUserId());
    }
}

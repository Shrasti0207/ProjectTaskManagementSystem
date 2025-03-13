package com.example.ProjectTaskManagement.Services;
import com.example.ProjectTaskManagement.Entities.UserEntity;
import com.example.ProjectTaskManagement.Exceptions.DuplicateResourceException;
import com.example.ProjectTaskManagement.Exceptions.ResourceNotFoundException;
import com.example.ProjectTaskManagement.Exceptions.ValidationException;
import com.example.ProjectTaskManagement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// This class is used to implement the business logic of the User.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // This method is used to create a new user.
    public UserEntity createUser(UserEntity userEntity){
        if (userRepository.existsByEmail(userEntity.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + userEntity.getEmail());
        }

        if(!userEntity.getEmail().contains("@")){
            throw new ValidationException("Invalid email format: " + userEntity.getEmail());
        }
        return userRepository.save(userEntity);
    }

    // This method is used to get all the users.
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    // This method is used to get a user by its id.
    public UserEntity getUserByUserId(Long userId){
        Optional<UserEntity> opt = userRepository.findById(userId);
        if(opt.isPresent()){
            return opt.get();
        }else{
            throw new ResourceNotFoundException("Id "+userId+" not found");
        }
    }

    // This method is used to update a user.
    public UserEntity updateUser(Long userId, UserEntity updatedUser){
        Optional<UserEntity> opt = userRepository.findById(userId);
        if(opt.isPresent()){
            UserEntity existingUser = opt.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setRole(updatedUser.getRole());
            existingUser.setActive(updatedUser.getActive());
            return userRepository.save(existingUser);
        }else{
            throw new ResourceNotFoundException("Id "+userId+" not found");
        }
    }

    // This method is used to delete a user.
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}

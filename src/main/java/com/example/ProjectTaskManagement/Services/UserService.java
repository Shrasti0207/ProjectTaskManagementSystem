package com.example.ProjectTaskManagement.Services;
import com.example.ProjectTaskManagement.Entities.UserEntity;
import com.example.ProjectTaskManagement.Exceptions.DuplicateResourceException;
import com.example.ProjectTaskManagement.Exceptions.ResourceNotFoundException;
import com.example.ProjectTaskManagement.Exceptions.ValidationException;
import com.example.ProjectTaskManagement.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// This class is used to implement the business logic of the User.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserService.class);
    private final String cache_name = "user";

    // This method is used to create a new user.
    @CachePut(cacheNames = cache_name, key = "#result.userId")
    public UserEntity createUser(UserEntity userEntity){
        logger.info("Creating a New User: {}", userEntity);
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
    @Cacheable(cacheNames = cache_name, key = "#userId")
    public UserEntity getUserByUserId(Long userId){
        logger.info("get user by id: {}", userId);
        Optional<UserEntity> opt = userRepository.findById(userId);
        if(opt.isPresent()){
            return opt.get();
        }else{
            throw new ResourceNotFoundException("Id "+userId+" not found");
        }
    }

    // This method is used to update a user.
    @CachePut(cacheNames = cache_name, key = "#userId")
    public UserEntity updateUser(Long userId, UserEntity updatedUser){
        logger.info("update user by id: {}", userId);
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
    @CacheEvict(cacheNames = cache_name, key = "#userId")
    public void deleteUser(Long userId){
        logger.info("delete user by id: {}", userId);
        userRepository.deleteById(userId);
    }
}

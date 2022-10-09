package com.techschool.service;

import com.techschool.carriers.ServiceResponse;
import com.techschool.entity.User;
import com.techschool.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public void createNewUser(User user, ServiceResponse response) {
        try {
            if (user == null || user.getFirstName() == null || user.getLastName() == null) {
                response.setStatus(BAD_REQUEST);
                response.setMessage("Failure");
            } else if (userRepository.findByFirstNameAndLastName(user.getFirstName(), user.getLastName()).isPresent()) {
                response.setStatus(BAD_REQUEST);
                response.setMessage("User already exists");
            } else {
                User savedUser = userRepository.saveAndFlush(user);
                response.setStatus(CREATED);
                response.setMessage("/users/"+user.getId());
                response.setResponse(savedUser);
            }
        } catch (Exception e) {
            logger.error("UserService.createNewUser", e);
        }
    }

    public void fetchAllUsers(ServiceResponse response) {
        try {
            List<User> allUsers = userRepository.findAll();
            if (allUsers == null || allUsers.isEmpty()) {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("No Users Found");
                response.setResponse(allUsers);
                return;
            }
            response.setResponse(allUsers);
        } catch (Exception e) {
            logger.error("UserService.fetchAllUsers", e);
        }
    }
}

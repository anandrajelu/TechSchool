package com.techschool.contoller;

import com.techschool.carriers.ServiceResponse;
import com.techschool.entity.User;
import com.techschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> fetchAllUsers() {
        ServiceResponse response = new ServiceResponse();
        userService.fetchAllUsers(response);
        return getResponseEntity(response);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        ServiceResponse response = new ServiceResponse();
        userService.createNewUser(user, response);
        return getResponseEntity(response);
    }
}

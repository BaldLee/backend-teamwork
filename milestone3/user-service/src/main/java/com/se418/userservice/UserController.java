package com.se418.userservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/id/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/signin")
    public User postUser(@RequestBody User user){
        return userRepository.save(user);
    }


    @GetMapping("/users/username/{username}")
    public User getByUsername(@PathVariable String username){
        return userRepository.findByUsername(username).orElse(null);
    }
}

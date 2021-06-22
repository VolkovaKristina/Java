package com.trello.trello.controller;


import com.trello.trello.entity.User;
import com.trello.trello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("trello")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/AllUsers")
    private ResponseEntity<List<User>> getAll(){
        final List<User> users = UserRepository.findAll();
        if (!users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/user/id={id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        final User found = UserRepository.findUserByRowId(id);
        if (found != null){
            return new ResponseEntity<>(found, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="AddUser")
    private ResponseEntity<?> create(@RequestBody User user){
        User newUser = UserRepository.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "PutUser")
    public ResponseEntity<?> put(@RequestBody User user) {
        return new ResponseEntity<>(UserRepository.save(user),HttpStatus.OK);
    }

    @DeleteMapping(value = "user/id={id}")
    public ResponseEntity<User> delete(@PathVariable long id) {
        UserRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

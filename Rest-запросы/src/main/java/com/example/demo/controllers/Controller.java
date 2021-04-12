package com.example.demo.controllers;

import com.example.demo.models.Role;
import com.example.demo.models.Status;
import com.example.demo.models.StatusTask;
import com.example.demo.models.User;
import com.example.demo.repo.*;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;


@RestController
@RequestMapping("api/project")
public class Controller {

    private final RoleRepo roleRepo;
    private final StatusRepo statusRepo;
    private final StatusTaskRepo statusTaskRepo;
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;


    public  Controller(RoleRepo roleRepo,StatusRepo statusRepo, StatusTaskRepo statusTaskRepo,
                       TaskRepo taskRepo, UserRepo userRepo){
        this.roleRepo = roleRepo;
        this.statusRepo = statusRepo;
        this.statusTaskRepo = statusTaskRepo;
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }


    @PostMapping("/users")
    User createUser(@RequestParam String firstName, @RequestParam String lastName,
                          @RequestParam String middleName, @RequestParam String passwordHash,
                          @RequestParam String login) {

        User user = new User(firstName, lastName, middleName, passwordHash, login);
        return this.userRepo.save(user);
    }

    @PostMapping("/roles")
    Role createRole(@RequestParam String name){
        Role role = new Role(name);
        return this.roleRepo.save(role);
    }

    @PostMapping("/status")
    Status createStatus(@RequestParam String name){
        Status status = new Status(name);
        return this.statusRepo.save(status);
    }

    @PostMapping("/statusTasks")
    StatusTask createStatustASK(@RequestBody String statusTask) throws JSONException {
        JSONObject rawStatusTask = new JSONObject(statusTask);
        StatusTask task = new StatusTask(rawStatusTask.getString("name"));
        task.setStatus(this.statusRepo.findStatusByRowId(rawStatusTask.getJSONObject("status").getLong("rowid")));
        return this.statusTaskRepo.save(task);

    }

    @GetMapping("/GETuser/{id}")
    User getUser(@PathVariable Long id) {

//        curl -X GET http://127.0.0.1:8080/api/project/GETuser/2
        return this.userRepo.findUserByRowId(id);
    }


    @GetMapping("/GETrole/{name}")
    Role getRole(@PathVariable String name) {

        return this.roleRepo.findRoleByName(name);
    }

    @DeleteMapping("/deleteUsers")
    User deleteUser(@RequestParam Long id){
        User foundUser = this.userRepo.findUserByRowId(id);
        this.userRepo.delete(foundUser);
        return foundUser;
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User newUser){
        User user  = this.userRepo.findUserByRowId(newUser.getRowId());
        user.setRowId(newUser.getRowId());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setMiddleName(newUser.getMiddleName());
        user.setLogin(newUser.getLogin());
        user.setPasswordHash(newUser.getPasswordHash());

        return this.userRepo.save(user);
    }







}

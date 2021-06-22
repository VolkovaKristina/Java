package com.trello.trello.controller;


import com.trello.trello.entity.StatusTask;
import com.trello.trello.repository.StatusTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("trello")
public class StatusTaskController {
    private final StatusTaskRepository statusTaskRepository;

    @Autowired
    public StatusTaskController(StatusTaskRepository statusTaskRepository) {
        this.statusTaskRepository = statusTaskRepository;
    }

    @GetMapping(value = "/AllStatusTasks")
    private ResponseEntity<List<StatusTask>> getAll(){
        final List<StatusTask> statusesTasks= StatusTaskRepository.findAll();
        if (!statusesTasks.isEmpty()){
            return new ResponseEntity<>(statusesTasks, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/statusTask/id={id}")
    public ResponseEntity<StatusTask> getStatus(@PathVariable Long id) {
        final StatusTask found = StatusTaskRepository.findStatusTaskByRowId(id);
        if(found != null){
            return new ResponseEntity<>(found, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="AddStatusTask")
    private ResponseEntity<?> create(@RequestBody StatusTask statusTask){
        StatusTask newStatusTask = StatusTaskRepository.save(statusTask);
        return new ResponseEntity<>(newStatusTask, HttpStatus.CREATED);
    }

    @PutMapping(value = "PutStatusTask")
    public ResponseEntity<?> put(@RequestBody StatusTask statusTask) {
        return new ResponseEntity<>(StatusTaskRepository.save(statusTask),HttpStatus.OK);
    }

    @DeleteMapping(value = "statusTask/id={id}")
    public ResponseEntity<StatusTask> delete(@PathVariable long id) {
        statusTaskRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

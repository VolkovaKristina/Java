package com.trello.trello.controller;


import com.trello.trello.entity.Status;
import com.trello.trello.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("trello")
public class StatusController {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping(value = "/AllAtatuses")
    private ResponseEntity<List<Status>> getAll(){
        final List<Status> statuses = StatusRepository.findAll();
        if(!statuses.isEmpty()){
            return new ResponseEntity<>(statuses, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/status/id={id}")
    public ResponseEntity<Status> getStatus(@PathVariable Long id) {
        final Status found = StatusRepository.findStatusByRowId(id);
        if (found != null){
            return new ResponseEntity<>(found, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="AddStatus")
    private ResponseEntity<?> create(@RequestBody Status status){
        Status newStatus = StatusRepository.save(status);
        return new ResponseEntity<>(newStatus, HttpStatus.CREATED);
    }

    @PutMapping(value = "PutStatus")
    public ResponseEntity<?> put(@RequestBody Status status) {
        return new ResponseEntity<>(StatusRepository.save(status),HttpStatus.OK);
    }

    @DeleteMapping(value = "status/id={id}")
    public ResponseEntity<Status> delete(@PathVariable long id) {
        statusRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

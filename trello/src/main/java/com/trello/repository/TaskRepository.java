package com.trello.trello.repository;


import com.trello.trello.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findTaskByRowId(long id);
}

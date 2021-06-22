package com.trello.trello.repository;


import com.trello.trello.entity.StatusTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusTaskRepository extends JpaRepository<StatusTask, Long> {
    StatusTask findStatusTaskByRowId(long id);
}

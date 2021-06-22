package com.trello.trello.repository;

import com.trello.trello.entity.Status;
import com.trello.trello.entity.StatusTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findStatusTaskByRowId(long id);
}

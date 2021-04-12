package com.example.demo.repo;

import com.example.demo.models.Role;
import com.example.demo.models.StatusTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusTaskRepo extends JpaRepository<StatusTask, Long> {
}

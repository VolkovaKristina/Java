package com.example.demo.repo;

import com.example.demo.models.Role;
import com.example.demo.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status, Long> {

    Status findStatusByRowId(Long id);
}

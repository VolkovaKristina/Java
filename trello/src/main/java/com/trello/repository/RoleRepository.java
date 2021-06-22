package com.trello.trello.repository;


import com.trello.trello.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findStatusTaskByRowId(long id);
}

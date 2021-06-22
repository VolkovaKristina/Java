package com.trello.trello.repository;


import com.trello.trello.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByRowId(long id);
}

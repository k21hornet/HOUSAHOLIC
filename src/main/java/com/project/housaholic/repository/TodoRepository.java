package com.project.housaholic.repository;

import com.project.housaholic.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, String> {
    List<Todo> findAllByUserId(String userId);
}

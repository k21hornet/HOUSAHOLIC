package com.project.housaholic.repository;

import com.project.housaholic.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, String> {
    List<Action> findAllByUserId(String userId);
}

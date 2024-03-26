package com.project.housaholic.service;

import com.project.housaholic.entity.Action;
import com.project.housaholic.entity.Category;
import com.project.housaholic.input.ActionInput;
import com.project.housaholic.repository.ActionRepository;
import com.project.housaholic.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActionService {
    private final CategoryRepository categoryRepository;
    private final ActionRepository actionRepository;

    public ActionService(CategoryRepository categoryRepository, ActionRepository actionRepository) {
        this.categoryRepository = categoryRepository;
        this.actionRepository = actionRepository;
    }

    public List<Category> getCategoriesById(String id) {
        return categoryRepository.getCategoryByUserId(id);
    }

    public List<Action> getActionsById(String userId) {
        return actionRepository.findAllByUserId(userId);
    }

    public void createTodo(ActionInput actionInput, String userId){
        Action action = new Action();
        action.setId(UUID.randomUUID().toString());
        action.setIsPositive(actionInput.getIsPositive());
        Optional<Category> category = categoryRepository.findById(actionInput.getCategoryId());
        action.setCategory(category.get());
        action.setTitle(actionInput.getTitle());
        action.setDescription(actionInput.getDescription());
        action.setUserId(userId);
        action.setPoint(actionInput.getPoint());
        action.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        action.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        actionRepository.saveAndFlush(action);
    }

    public void deleteTodo(String id) {
        actionRepository.deleteById(id);
    }

    public int getExp(String userId) {
        List<Action> actions = this.getActionsById(userId);
        int exp = 0;
        for(Action action : actions) {
            exp += action.getPoint();
        }
        return exp;
    }
}

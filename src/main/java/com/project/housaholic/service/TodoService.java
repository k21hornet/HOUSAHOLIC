package com.project.housaholic.service;

import com.project.housaholic.entity.Todo;
import com.project.housaholic.input.TodoInput;
import com.project.housaholic.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodosById(String id) {
        return todoRepository.findAllByUserId(id);
    }

    public void createTodo(TodoInput todoInput, String userId){
        Todo todo = new Todo();
        todo.setId(UUID.randomUUID().toString());
        todo.setTodo(todoInput.getTodo());
        todo.setUserId(userId);
        todo.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        todo.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        todoRepository.saveAndFlush(todo);
    }

    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }
}

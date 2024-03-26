package com.project.housaholic.controller;

import com.project.housaholic.entity.AppUser;
import com.project.housaholic.entity.Todo;
import com.project.housaholic.input.TodoInput;
import com.project.housaholic.service.ActionService;
import com.project.housaholic.service.AuthService;
import com.project.housaholic.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {
    private final AuthService authService;
    private final TodoService todoService;
    private final ActionService actionService;

    public TodoController(AuthService authService, TodoService todoService, ActionService actionService) {
        this.authService = authService;
        this.todoService = todoService;
        this.actionService = actionService;
    }

    @GetMapping
    public String getAllTodo(Model model){
        // ユーザー情報
        AppUser user = authService.getLoginUser();
        String username = user.getUsername();
        String userId = user.getId();
        model.addAttribute("username",username);

        // ユーザーのEXP
        int exp = actionService.getExp(userId);
        model.addAttribute("exp", exp);

        // TODO取得
        List<Todo> todos = todoService.getTodosById(userId);
        model.addAttribute("todos", todos);

        // TODO作成
        model.addAttribute("todoInput", new TodoInput());
        return "todo/todo";
    }

    @PostMapping("/create")
    public String createTodo(
            @Validated TodoInput todoInput, BindingResult result){
        if(result.hasErrors()){
            return "todo/todo";
        }
        String userId = authService.getLoginUser().getId();
        todoService.createTodo(todoInput, userId);
        return "redirect:/todo";
    }

    @PostMapping("/delete")
    public String deleteTodo(TodoInput todoInput) {
        todoService.deleteTodo(todoInput.getId());
        return "redirect:/todo";
    }
}

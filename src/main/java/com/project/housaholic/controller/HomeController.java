package com.project.housaholic.controller;

import com.project.housaholic.entity.Action;
import com.project.housaholic.entity.AppUser;
import com.project.housaholic.entity.Category;
import com.project.housaholic.input.ActionInput;
import com.project.housaholic.input.CategoryInput;
import com.project.housaholic.service.ActionService;
import com.project.housaholic.service.AuthService;
import com.project.housaholic.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    private final AuthService authService;
    private final ActionService actionService;
    private final CategoryService categoryService;

    public HomeController(AuthService authService, ActionService actionService, CategoryService categoryService) {
        this.authService = authService;
        this.actionService = actionService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(Model model){
        // ログイン中のユーザー
        AppUser user = authService.getLoginUser();
        String userId = user.getId();
        String username = user.getUsername();
        model.addAttribute("username", username);

        // ユーザーのEXP
        int exp = actionService.getExp(userId);
        model.addAttribute("exp", exp);

        // ユーザーの持っているカテゴリー
        List<Category> categories = actionService.getCategoriesById(userId);
        model.addAttribute("categories", categories);
        model.addAttribute("actionInput", new ActionInput());

        List<Action> actions = actionService.getActionsById(userId);
        model.addAttribute("actions", actions);
        return "index";
    }

    @PostMapping("/create")
    public String createAction(
            @Validated ActionInput actionInput, BindingResult result){
        if(result.hasErrors()){
            return "index";
        }
        String userId = authService.getLoginUser().getId();
        actionService.createTodo(actionInput, userId);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTodo(ActionInput actionInput) {
        if(actionInput.getId()!=null){
            actionService.deleteTodo(actionInput.getId());
        }
        return "redirect:/";
    }

    @GetMapping("/category")
    public String indexCategory(Model model){
        // ログイン中のユーザー
        AppUser user = authService.getLoginUser();
        String userId = user.getId();
        String username = user.getUsername();
        model.addAttribute("username", username);

        // ユーザーのEXP
        int exp = actionService.getExp(userId);
        model.addAttribute("exp", exp);

        // ユーザーの持っているカテゴリー
        List<Category> categories = categoryService.getCategoriesById(userId);
        model.addAttribute("categories", categories);
        model.addAttribute("categoryInput", new CategoryInput());
        return "category/category";
    }

    @PostMapping("/category/create")
    public String createCategory(
            @Validated CategoryInput categoryInput, BindingResult result){
        if(result.hasErrors()){
            return "category/category";
        }
        String userId = authService.getLoginUser().getId();
        categoryService.createCategory(categoryInput, userId);
        return "redirect:/category";
    }

    @PostMapping("/category/delete")
    public String deleteCategory(CategoryInput categoryInput) {
        if(categoryInput.getId()!=null){
            categoryService.deleteCategory(categoryInput.getId());
        }
        return "redirect:/category";
    }
}

package com.project.housaholic.controller;

import com.project.housaholic.input.SignupInput;
import com.project.housaholic.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("signupInput", new SignupInput());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @Validated SignupInput signupInput, BindingResult result, Model model
    ){
        if(result.hasErrors()){
            // 入力データを
            model.addAttribute(signupInput);
            String errorMessage = result.getAllErrors().stream()
                    .map(err -> err.getDefaultMessage())
                    // エラーメッセージをカンマで連結
                    .collect(Collectors.joining(", "));
            return redirectToErrorPage(errorMessage);
        }

        try {
            authService.createUser(signupInput);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:8080/login?status=ok"))
                    .body("User registered successfully.");
        } catch (Exception ex) {
            // 登録エラーのハンドリング
            return redirectToErrorPage(ex.getMessage());
        }

    }

    // エラーメッセージを持ったエラーページへのリダイレクト
    private ResponseEntity<String> redirectToErrorPage(String errorMessage) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/signup")
                .queryParam("error", errorMessage);

        URI errorPageUri = builder.build().encode().toUri();
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(errorPageUri)
                .body(errorMessage);
    }
}

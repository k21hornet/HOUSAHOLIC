package com.project.housaholic.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoInput {
    private String id;
    @NotBlank(message = "必須です")
    private String todo;
}

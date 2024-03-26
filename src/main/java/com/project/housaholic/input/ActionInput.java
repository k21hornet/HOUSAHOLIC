package com.project.housaholic.input;


import lombok.Data;

@Data
public class ActionInput {
    private String id;

    private Integer isPositive;

    private String title;

    private String description;

    private String categoryId;

    private Integer point;
}

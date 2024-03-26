package com.project.housaholic.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "action_table")
@Data
public class Action {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "is_positive")
    private Integer isPositive;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "point")
    private Integer point;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;
}

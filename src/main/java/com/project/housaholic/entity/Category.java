package com.project.housaholic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class Category {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "is_default")
    private Integer isDefault;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;
}

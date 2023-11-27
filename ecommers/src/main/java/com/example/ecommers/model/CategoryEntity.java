package com.example.ecommers.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a product category.
 *
 * This class is annotated with various annotations to define its role as an entity,
 * and it utilizes Lombok annotations for generating getters, setters, and constructors.
 *
 * @Entity Indicates that this class is a JPA entity, representing a table in the database.
 * @Data Lombok's annotation to automatically generate getter, setter, toString, equals, and hashCode methods.
 * @NoArgsConstructor Lombok's annotation to generate a no-argument constructor.
 * @AllArgsConstructor Lombok's annotation to generate an all-argument constructor.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    /**
     * The identifier for the category.
     *
     * @Id Indicates that this field is the primary key.
     * @GeneratedValue(strategy = GenerationType.IDENTITY) Specifies the strategy for generating the primary key value.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    /**
     * The name of the category.
     *
     * @Column(length = 40, nullable = false) Specifies the column constraints for the category name.
     */
    @Column(length = 40, nullable = false)
    private String category;

    /**
     * The status of the category.
     *
     * @Column(columnDefinition = "boolean default true", nullable = false) Specifies the column constraints for the category status.
     */
    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean status;
}


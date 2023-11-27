package com.example.ecommers.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a product.
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
public class ProductEntity {

    /**
     * The identifier for the product.
     *
     * @Id Indicates that this field is the primary key.
     * @GeneratedValue(strategy = GenerationType.IDENTITY) Specifies the strategy for generating the primary key value.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    /**
     * The name of the product.
     *
     * @Column(length = 100, nullable = false) Specifies the column constraints for the product name.
     */
    @Column(length = 100, nullable = false)
    private String productName;

    /**
     * The available quantity of the product in the inventory.
     *
     * @Column(nullable = false) Specifies the column constraints for the product stock.
     */
    @Column(nullable = false)
    private int productStock;

    /**
     * The URL of the image associated with the product.
     *
     * @Column(length = 200) Specifies the column constraints for the product image URL.
     */
    @Column(length = 200)
    private String productImg;

    /**
     * The price of the product.
     *
     * @Column(nullable = false) Specifies the column constraints for the product price.
     */
    @Column(nullable = false)
    private double productPrice;

    /**
     * The status of the product.
     *
     * @Column(columnDefinition = "boolean default true") Specifies the column constraints for the product status.
     */
    @Column(columnDefinition = "boolean default true")
    private Boolean status;

    /**
     * The category of the product.
     *
     * @ManyToOne Indicates a Many-to-One relationship with the CategoryEntity.
     * @JoinColumn(name = "id_category", nullable = false) Specifies the join column for the relationship.
     */
    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private CategoryEntity typeCategory;

}


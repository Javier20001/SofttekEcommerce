package com.example.ecommers.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a bid.
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
public class BidEntity {

    /**
     * The identifier for the bid.
     *
     * @Id Indicates that this field is the primary key.
     * @GeneratedValue(strategy = GenerationType.IDENTITY) Specifies the strategy for generating the primary key value.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The category of the bid.
     *
     * @ManyToOne Indicates a Many-to-One relationship with the UserEntity.
     * @JoinColumn(name = "id_user", nullable = false) Specifies the join column for the relationship.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UserEntity user;

    @OneToMany(mappedBy = "bid" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ItemBidEntity> items = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dir_id", referencedColumnName = "id")
    private DirEntity dir;
}

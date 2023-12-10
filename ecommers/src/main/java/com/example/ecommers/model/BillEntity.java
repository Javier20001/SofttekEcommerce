package com.example.ecommers.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBill;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UserEntity user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dir", referencedColumnName = "id_dir")
    private DirEntity dir;
}


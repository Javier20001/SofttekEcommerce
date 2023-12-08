package com.example.ecommers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class DirEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String street;

    @Column
    @NotBlank
    private String city;

    @Column
    @NotBlank
    private String state;

    @Column
    @NotBlank
    private String postalCode;
}


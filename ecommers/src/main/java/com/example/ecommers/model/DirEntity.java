package com.example.ecommers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class DirEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dir")
    private Long id;

    @Column
    @NotBlank
    private String street;

    @Column
    @NotBlank
    private String province;

    @Column
    @NotBlank
    private String locality;

    @Column
    @NotBlank
    private String streetNumber;
    @Column
    private Boolean isApartment;

    @Column
    private String floorNumber;

    @Column
    private String apartmentNumber;

    @Column
    private LocalDate selectedDate;
}
/*province: "",
    locality: "",
    street: "",
    streetNumber: "",
    isApartment: false,
    apartmentNumber: "",
    floorNumber: "",
    paymentMPDTOFromFrontend,
    selectedDate: null,*/

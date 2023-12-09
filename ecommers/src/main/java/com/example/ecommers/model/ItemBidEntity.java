package com.example.ecommers.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemBidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long idProduct;

    @ManyToOne
    @JoinColumn(name = "id_bid", nullable = false)
    private BidEntity bid;

    @Column
    private Integer quantitySelected;

}

package com.example.ecommers.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemBidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private Long idProduct;

    @ManyToOne
    @JoinColumn(name = "bid_id")
    private BidEntity bid;
    @Column
    private Integer quantitySelected;





}

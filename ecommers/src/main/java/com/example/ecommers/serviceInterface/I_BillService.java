package com.example.ecommers.serviceInterface;

import com.example.ecommers.dto.BillDTO;
import com.example.ecommers.model.BillEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service interface defining the contract for managing Bids.
 * Implementing classes should provide the business logic for CRUD operations on Bids.
 */

public interface I_BillService {

    /**
     * Retrieves a list of all products.
     *
     * @return List<BidEntity> A list of all bids.
     * @see BillEntity
     */
    List<BillEntity> getAllBill();

    /**
     * Retrieves a bid by its identifier.
     *
     * @param id The identifier of the bids.
     * @return Optional<BidEntity> An Optional containing the bid, or empty if not found.
     * @see BillEntity
     */
    Optional<BillEntity> getBillById(Long id);

    /**
     * Saves a new Bid.
     *
     * @param bid The bid entity to be saved.
     * @return BidEntity The saved bid.
     * @see BillEntity
     */
    BillEntity saveBill(BillDTO bid);

    Integer countBill();

}

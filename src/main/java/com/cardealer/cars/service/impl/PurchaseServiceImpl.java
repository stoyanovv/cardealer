package com.cardealer.cars.service.impl;

import com.cardealer.cars.model.entity.Purchase;
import com.cardealer.cars.repository.PurchaseRepository;
import com.cardealer.cars.service.PurchaseService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }


    @Override
    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}

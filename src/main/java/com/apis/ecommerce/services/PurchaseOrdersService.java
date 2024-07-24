package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.PurchaseOrder;
import com.apis.ecommerce.entities.dto.PurchaseOrderRequest;
import com.apis.ecommerce.exceptions.InsufficientStockException;
import com.apis.ecommerce.exceptions.InvalidUnitsException;
import com.apis.ecommerce.exceptions.ProductNonexistentException;
import com.apis.ecommerce.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PurchaseOrdersService {
    PurchaseOrder createPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest) throws InvalidUnitsException, InsufficientStockException, ProductNonexistentException, UserNotFoundException;

    Optional<PurchaseOrder> getPurchaseOrderById(Long id);

    List<PurchaseOrder> getAllPurchaseOrders();

    PurchaseOrder deletePurchaseOrderById(Long id);
}

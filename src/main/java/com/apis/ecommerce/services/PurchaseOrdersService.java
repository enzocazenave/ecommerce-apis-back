package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.PurchaseOrder;
import com.apis.ecommerce.entities.dto.PurchaseOrderRequest;
import com.apis.ecommerce.exceptions.InsufficientStockException;
import com.apis.ecommerce.exceptions.InvalidPriceOrUnitProductException;
import com.apis.ecommerce.exceptions.ProductNonexistentException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PurchaseOrdersService {
    PurchaseOrder createPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest) throws InvalidPriceOrUnitProductException, InsufficientStockException, ProductNonexistentException;

    Optional<PurchaseOrder> getPurchaseOrderById(Long id);

    List<PurchaseOrder> getAllPurchaseOrders();

    PurchaseOrder deletePurchaseOrderById(Long id);
}

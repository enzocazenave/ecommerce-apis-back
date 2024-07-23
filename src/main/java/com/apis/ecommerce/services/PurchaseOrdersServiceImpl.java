package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.*;
import com.apis.ecommerce.entities.dto.PurchaseOrderRequest;
import com.apis.ecommerce.entities.dto.PurchasedProductRequest;
import com.apis.ecommerce.enums.DiscountStatus;
import com.apis.ecommerce.enums.PurchaseOrderStatus;
import com.apis.ecommerce.repositories.ProductRepository;
import com.apis.ecommerce.repositories.PurchaseOrdersRepository;
import com.apis.ecommerce.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrdersServiceImpl implements PurchaseOrdersService {

    @Autowired
    private PurchaseOrdersRepository purchaseOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder deletePurchaseOrderById(Long id) {

        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
        if (!purchaseOrder.isPresent()) {
            return null;
        }
        purchaseOrder.get().setStatus(PurchaseOrderStatus.DELETED);
        return purchaseOrderRepository.save(purchaseOrder.get());
    }

    public PurchaseOrder createPurchaseOrderwithDiscountCode(DiscountCoupon discountCoupon, PurchaseOrderRequest purchaseOrderRequest) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        Double totalPrice = 0.0;
        List<PurchasedProduct> purchasedProducts = new ArrayList<>();

        for (PurchasedProductRequest purchasedProductRequest : purchaseOrderRequest.getPurchasedProductRequests()) {
            Optional<Product> productOptional = productRepository.findById(purchasedProductRequest.getProductId());
            if (!productOptional.isPresent()) {
                return null;
            }

            if (productOptional.get().getStock() < 0) {
                //TODO: BadRequest because we have not stock available
                return null;
            }

            PurchasedProduct purchasedProduct = new PurchasedProduct();
            purchasedProduct.setPrice(purchasedProductRequest.getPrice());
            purchasedProduct.setUnit(purchasedProductRequest.getUnit());
            purchasedProduct.setProductId(purchasedProductRequest.getProductId());

            totalPrice = totalPrice + (purchasedProduct.getPrice() * purchasedProduct.getUnit());

            purchasedProducts.add(purchasedProduct);

            productOptional.get().setStock(productOptional.get().getStock() - purchasedProductRequest.getUnit());
            productRepository.save(productOptional.get());
        }

        Optional<User> user = usersRepository.findById(purchaseOrderRequest.getUserId());
        purchaseOrder.setUser(user.get());
        purchaseOrder.setDateCreated(new Date());
        purchaseOrder.setStatus(PurchaseOrderStatus.APPROVED);
        purchaseOrder.setDiscountCoupon(discountCoupon);
        purchaseOrder.setTotalPrice(totalPrice - totalPrice * discountCoupon.getPercentage());
        purchaseOrder.setPurchasedProducts(purchasedProducts);


        return purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder createPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        Optional<User> user = usersRepository.findById(purchaseOrderRequest.getUserId());
        purchaseOrder.setUser(user.get());
        Double totalPrice = 0.0;

        List<PurchasedProduct> purchasedProducts = new ArrayList<>();
        for (PurchasedProductRequest purchasedProductRequest : purchaseOrderRequest.getPurchasedProductRequests()) {
            PurchasedProduct purchasedProduct = new PurchasedProduct();
            purchasedProduct.setProductId(purchasedProductRequest.getProductId());
            purchasedProduct.setPrice(purchasedProductRequest.getPrice());
            purchasedProduct.setUnit(purchasedProductRequest.getUnit());

            totalPrice = totalPrice + (purchasedProduct.getPrice() * purchasedProductRequest.getUnit());
            purchasedProducts.add(purchasedProduct);
        }

        purchaseOrder.setPurchasedProducts(purchasedProducts);
        purchaseOrder.setDateCreated(new Date());
        purchaseOrder.setStatus(PurchaseOrderStatus.APPROVED);
        purchaseOrder.setDiscountCoupon(new DiscountCoupon());
        purchaseOrder.setTotalPrice(totalPrice);

        return purchaseOrderRepository.save(purchaseOrder);
    }
}

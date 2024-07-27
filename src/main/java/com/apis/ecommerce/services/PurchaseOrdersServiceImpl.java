package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.*;
import com.apis.ecommerce.entities.dto.ProductResponse;
import com.apis.ecommerce.entities.dto.PurchaseOrderRequest;
import com.apis.ecommerce.entities.dto.PurchasedProductRequest;
import com.apis.ecommerce.enums.PurchaseOrderStatus;
import com.apis.ecommerce.exceptions.InsufficientStockException;
import com.apis.ecommerce.exceptions.ProductNonexistentException;
import com.apis.ecommerce.exceptions.UserNotFoundException;
import com.apis.ecommerce.repositories.PurchaseOrdersRepository;

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
    private ProductService productService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private DiscountCouponsService discountCouponsService;

    @Autowired
    private PurchasedProductsService purchasedProductsService;

    public PurchaseOrder createPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest) throws InsufficientStockException, ProductNonexistentException, UserNotFoundException {
        double totalPrice = 0.0;
        List<PurchasedProduct> purchasedProducts = new ArrayList<>();

        for (PurchasedProductRequest purchasedProductRequest : purchaseOrderRequest.getPurchasedProductRequests()) {
            Integer units = purchasedProductRequest.getUnits();
            Long productId = purchasedProductRequest.getProductId();

            Optional<ProductResponse> productOptional = productService.getProductById(productId);
            if (productOptional.isEmpty()) {
                throw new ProductNonexistentException();
            }

            ProductResponse product = productOptional.get();

            if (product.getStock() < units) {
                throw new InsufficientStockException();
            }

            Double price = product.getPrice();

            PurchasedProduct purchasedProduct = new PurchasedProduct();
            purchasedProduct.setPrice(price);
            purchasedProduct.setUnit(units);
            purchasedProduct.setProductId(productId);
            totalPrice += price * units;
            purchasedProducts.add(purchasedProduct);

            productService.reduceStockBy(productId, units);
        }

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setTotalPrice(totalPrice);

        //DiscountCoupon logic
        Optional<DiscountCoupon> discountCouponOptional = discountCouponsService.getDiscountCouponByCode(purchaseOrderRequest.getDiscountCode());
        if (discountCouponOptional.isPresent()) {
            DiscountCoupon discountCoupon = discountCouponOptional.get();

            purchaseOrder.setTotalPrice(totalPrice - totalPrice * discountCoupon.getPercentage() / 100);
            purchaseOrder.setDiscountCoupon(discountCoupon);
            discountCouponsService.reduceStockByOne(discountCoupon.getId());
        }

        purchaseOrder.setDateCreated(new Date());
        purchaseOrder.setStatus(PurchaseOrderStatus.APPROVED);

        //User Logic
        Optional<User> user = usersService.getUserById(purchaseOrderRequest.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        purchaseOrder.setUser(user.get());

        PurchaseOrder  persitedPurchaseOrder =  purchaseOrderRepository.save(purchaseOrder);
        for (PurchasedProduct purchasedProduct : purchasedProducts) {
            purchasedProduct.setPurchaseOrder(persitedPurchaseOrder);
            purchasedProduct.setDateCreated(new Date());
            purchasedProductsService.createPurchasedProduct(purchasedProduct);
        }

        return persitedPurchaseOrder;
    }

    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder deletePurchaseOrderById(Long id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
        if (purchaseOrder.isEmpty()) {
            return null;
        }
        purchaseOrder.get().setStatus(PurchaseOrderStatus.DELETED);
        return purchaseOrderRepository.save(purchaseOrder.get());
    }
}
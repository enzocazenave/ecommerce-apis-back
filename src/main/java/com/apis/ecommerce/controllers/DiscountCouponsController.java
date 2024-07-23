package com.apis.ecommerce.controllers;

import com.apis.ecommerce.entities.DiscountCoupon;
import com.apis.ecommerce.exceptions.CategoryDuplicateException;
import com.apis.ecommerce.services.DiscountCouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/discount_coupons")
public class DiscountCouponsController {
    @Autowired
    private DiscountCouponsService discountCouponService;

    @GetMapping
    public List<DiscountCoupon> getDiscountCoupons() {
        return discountCouponService.getDiscountCoupons();
    }

    @GetMapping("/{id}")
    public DiscountCoupon getDiscountCouponById(@PathVariable Long id) {
        return discountCouponService.getDiscountCouponById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createDiscountCoupon(@RequestBody DiscountCoupon discountCoupon) throws CategoryDuplicateException, URISyntaxException {
        DiscountCoupon createdDiscountCoupon = discountCouponService.createDiscountCoupon(discountCoupon);
        URI location = new URI("/discount_coupons/" + createdDiscountCoupon.getId());
        return ResponseEntity.created(location).body(createdDiscountCoupon);
    }
}

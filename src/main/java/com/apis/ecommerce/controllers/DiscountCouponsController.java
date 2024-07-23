package com.apis.ecommerce.controllers;

import com.apis.ecommerce.entities.DiscountCoupon;
import com.apis.ecommerce.entities.PurchaseOrder;
import com.apis.ecommerce.entities.dto.DiscountCouponRequest;
import com.apis.ecommerce.services.DiscountCouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<DiscountCoupon> getDiscountCouponById(@PathVariable Long id) {
        Optional<DiscountCoupon> discountCoupon = discountCouponService.getDiscountCouponById(id);
        if (discountCoupon.isPresent()) {
            return ResponseEntity.ok(discountCoupon.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DiscountCoupon> createDiscountCoupon(@RequestBody DiscountCouponRequest discountCouponRequest) throws URISyntaxException {
        DiscountCoupon createdDiscountCoupon = discountCouponService.createDiscountCoupon(discountCouponRequest);
        URI location = new URI("/discount_coupons/" + createdDiscountCoupon.getId());
        return ResponseEntity.created(location).body(createdDiscountCoupon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DiscountCoupon> deleteDiscountCouponById(@PathVariable Long id) {
        Optional<DiscountCoupon> deletedDiscountCoupon = discountCouponService.deleteDiscountCouponById(id);
        if (!deletedDiscountCoupon.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscountCoupon> updateDiscountCoupon(@PathVariable Long id, @RequestBody DiscountCouponRequest discountCouponRequest) throws URISyntaxException {

        Optional<DiscountCoupon> updatedDiscountCoupon = discountCouponService.updateDiscountCoupon(id, discountCouponRequest);

        if (updatedDiscountCoupon.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedDiscountCoupon.get());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<DiscountCoupon> getDiscountCouponByDiscountCode(@PathVariable String code) {
        Optional<DiscountCoupon>  discountCoupons = discountCouponService.getDiscountCouponByCode(code);
        return ResponseEntity.ok(discountCoupons.get());
    }
}


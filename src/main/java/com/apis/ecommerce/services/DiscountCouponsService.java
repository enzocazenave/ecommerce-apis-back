package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.DiscountCoupon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DiscountCouponsService {
    public DiscountCoupon createDiscountCoupon(DiscountCoupon discountCoupon);

    public Optional<DiscountCoupon> getDiscountCouponById(Long id);

    public List<DiscountCoupon> getDiscountCoupons();
}

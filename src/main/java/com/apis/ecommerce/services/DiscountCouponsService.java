package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.DiscountCoupon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiscountCouponsService {
    public DiscountCoupon createDiscountCoupon(DiscountCoupon discountCoupon);

    public DiscountCoupon getDiscountCouponById(Long id);

    public List<DiscountCoupon> getDiscountCoupons();
}

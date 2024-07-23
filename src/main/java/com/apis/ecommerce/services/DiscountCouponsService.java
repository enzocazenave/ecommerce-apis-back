package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.DiscountCoupon;
import com.apis.ecommerce.entities.dto.DiscountCouponRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DiscountCouponsService {
    public DiscountCoupon createDiscountCoupon(DiscountCouponRequest discountCouponRequest);

    public Optional<DiscountCoupon> getDiscountCouponById(Long id);

    public List<DiscountCoupon> getDiscountCoupons();

    public DiscountCoupon addProductToDiscountCoupon(Long productId, Long discountCouponId);
}

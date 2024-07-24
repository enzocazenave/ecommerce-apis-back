package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.DiscountCoupon;
import com.apis.ecommerce.entities.dto.DiscountCouponRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DiscountCouponsService {
    public DiscountCoupon createDiscountCoupon(DiscountCouponRequest discountCouponRequest);

    public List<DiscountCoupon> getDiscountCoupons();

    public Optional<DiscountCoupon> getDiscountCouponById(Long id);

    public Optional<DiscountCoupon> updateDiscountCoupon(Long id, DiscountCouponRequest discountCouponRequest);

    public Optional<DiscountCoupon> deleteDiscountCouponById(Long id);

    public boolean isCouponEligible(Long id);

    public Optional<DiscountCoupon>  getDiscountCouponByCode(String code);

    public DiscountCoupon reduceStockByOne(Long couponId);

    }

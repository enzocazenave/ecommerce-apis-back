package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.DiscountCoupon;
import com.apis.ecommerce.repositories.DiscountCouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountCouponsServiceImpl implements DiscountCouponsService {

    @Autowired
    private DiscountCouponsRepository discountCouponRepository;

    public DiscountCoupon createDiscountCoupon(DiscountCoupon discountCoupon) {
        return discountCouponRepository.save(discountCoupon);
    }

    public Optional<DiscountCoupon> getDiscountCouponById(Long id) {
        return discountCouponRepository.findById(id);
    }

    public List<DiscountCoupon> getDiscountCoupons() {
        return discountCouponRepository.findAll();
    }
}
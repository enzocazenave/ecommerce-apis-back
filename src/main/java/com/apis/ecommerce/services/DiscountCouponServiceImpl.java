package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.DiscountCoupon;
import com.apis.ecommerce.repositories.DiscountCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountCouponServiceImpl implements DiscountCouponService {

    @Autowired
    private DiscountCouponRepository discountCouponRepository;

    public DiscountCoupon createDiscountCoupon(DiscountCoupon discountCoupon) {
        return discountCouponRepository.save(discountCoupon);
    }

    public DiscountCoupon getDiscountCouponById(Long id) {
        return discountCouponRepository.findById(id).get();
    }

    public List<DiscountCoupon> getDiscountCoupons() {
        return discountCouponRepository.findAll();
    }
}

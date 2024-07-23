package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.DiscountCoupon;
import com.apis.ecommerce.entities.dto.DiscountCouponRequest;
import com.apis.ecommerce.repositories.DiscountCouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountCouponsServiceImpl implements DiscountCouponsService {

    @Autowired
    private DiscountCouponsRepository discountCouponRepository;

    public DiscountCoupon createDiscountCoupon(DiscountCouponRequest discountCouponRequest) {
        DiscountCoupon discountCoupon = new DiscountCoupon(discountCouponRequest);
        return discountCouponRepository.save(discountCoupon);
    }

    public Optional<DiscountCoupon> getDiscountCouponById(Long id) {
        return discountCouponRepository.findById(id);
    }

    public List<DiscountCoupon> getDiscountCoupons() {
        return discountCouponRepository.findAll();
    }

    public DiscountCoupon addProductToDiscountCoupon(Long productId, Long discountCouponId) {
        Optional<DiscountCoupon> discountCoupon = discountCouponRepository.findById(discountCouponId);
        if (!discountCoupon.isPresent()) {
            return null;
        }

        return null;
    }
}

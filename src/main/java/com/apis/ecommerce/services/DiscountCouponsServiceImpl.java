package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.DiscountCoupon;
import com.apis.ecommerce.entities.dto.DiscountCouponRequest;
import com.apis.ecommerce.enums.DiscountStatus;
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

    public List<DiscountCoupon> getDiscountCoupons() {
        return discountCouponRepository.findAll();
    }

    public Optional<DiscountCoupon> getDiscountCouponById(Long id) {
        return discountCouponRepository.findById(id);
    }

    public DiscountCoupon updateDiscountCoupon(Long id, DiscountCouponRequest discountCouponRequest) {

        Optional<DiscountCoupon> discountCouponOptional = discountCouponRepository.findById(id);
        if (!discountCouponOptional.isPresent()) {
            return null;
        }

        DiscountCoupon discountCoupon = discountCouponOptional.get();

        if (discountCouponRequest.getCount() != null) {
            discountCoupon.setCount(discountCouponRequest.getCount());
        }
        if (discountCouponRequest.getCode() != null) {
            discountCoupon.setCode(discountCouponRequest.getCode());
        }
        if (discountCouponRequest.getStatus() != null) {
            discountCoupon.setStatus(discountCouponRequest.getStatus());
        }
        if (discountCouponRequest.getPercentage() != null) {
            discountCoupon.setPercentage(discountCouponRequest.getPercentage());
        }

        return discountCouponRepository.save(discountCoupon);
    }


    public DiscountCoupon deleteDiscountCouponById(Long id) {
        Optional<DiscountCoupon> discountCoupon = discountCouponRepository.findById(id);
        if (!discountCoupon.isPresent()) {
            return null;
        }
        discountCoupon.get().setStatus(DiscountStatus.EXPIRED);
        return discountCouponRepository.save(discountCoupon.get());
    }

    public boolean isCouponEligible(Long id) {
        Optional<DiscountCoupon> discountCouponOptional = discountCouponRepository.findById(id);
        if (!discountCouponOptional.isPresent()) {
            return false;
        }

        DiscountCoupon discountCoupon = discountCouponOptional.get();

        if (!discountCoupon.getStatus().equals(DiscountStatus.ACTIVE)){
            return false;
        }
        return true;
    }
}

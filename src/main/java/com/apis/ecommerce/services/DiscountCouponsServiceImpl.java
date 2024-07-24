package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.DiscountCoupon;
import com.apis.ecommerce.entities.dto.DiscountCouponRequest;
import com.apis.ecommerce.enums.DiscountStatus;
import com.apis.ecommerce.repositories.DiscountCouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Optional<DiscountCoupon> updateDiscountCoupon(Long id, DiscountCouponRequest discountCouponRequest) {

        Optional<DiscountCoupon> discountCouponOptional = discountCouponRepository.findById(id);
        if (!discountCouponOptional.isPresent()) {
            return null;
        }

        DiscountCoupon discountCoupon = discountCouponOptional.get();

        if (discountCouponRequest.getAvailableQuantity() != null) {
            discountCoupon.setAvailableQuantity(discountCouponRequest.getAvailableQuantity());
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

        discountCoupon.setLastUpdated(new Date());
        DiscountCoupon updatedDiscountCoupon = discountCouponRepository.save(discountCoupon);
        return Optional.of(updatedDiscountCoupon);
    }


    public Optional<DiscountCoupon> deleteDiscountCouponById(Long id) {
        Optional<DiscountCoupon> discountCouponOptional = discountCouponRepository.findById(id);

        if (!discountCouponOptional.isPresent()) {
            return Optional.empty();
        }

        DiscountCoupon discountCoupon = discountCouponOptional.get();
        discountCoupon.setStatus(DiscountStatus.EXPIRED);
        discountCoupon.setLastUpdated(new Date());
        DiscountCoupon updatedDiscountCoupon = discountCouponRepository.save(discountCoupon);

        return Optional.of(updatedDiscountCoupon);

    }

    public boolean isCouponEligible(Long id) {
        Optional<DiscountCoupon> discountCouponOptional = discountCouponRepository.findById(id);
        if (!discountCouponOptional.isPresent()) {
            return false;
        }

        DiscountCoupon discountCoupon = discountCouponOptional.get();

        if (!discountCoupon.getStatus().equals(DiscountStatus.ACTIVE)) {
            return false;
        }
        return true;
    }

    public Optional<DiscountCoupon> getDiscountCouponByCode(String code) {
        Optional<DiscountCoupon> discountCouponList = discountCouponRepository.findByCode(code);
        return discountCouponList;
    }

    public DiscountCoupon reduceStockByOne(Long id) {
        Optional<DiscountCoupon> discountCouponOptional = discountCouponRepository.findById(id);
        if (!discountCouponOptional.isPresent()) {
            return null;
        }

        DiscountCoupon discountCoupon = discountCouponOptional.get();
        discountCoupon.setAvailableQuantity(discountCoupon.getAvailableQuantity() - 1);

        return discountCouponRepository.save(discountCoupon);
    }
}

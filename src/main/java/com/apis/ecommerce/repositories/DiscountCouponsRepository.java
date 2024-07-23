package com.apis.ecommerce.repositories;

import com.apis.ecommerce.entities.DiscountCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCouponsRepository extends JpaRepository<DiscountCoupon, Long> {
}

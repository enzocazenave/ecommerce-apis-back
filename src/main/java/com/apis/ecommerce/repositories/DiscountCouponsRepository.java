package com.apis.ecommerce.repositories;

import com.apis.ecommerce.entities.DiscountCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountCouponsRepository extends JpaRepository<DiscountCoupon, Long> {

    @Query("SELECT d FROM DiscountCoupon d WHERE d.code = ?1")
    List<DiscountCoupon> findByCode(String code);
}

package com.apis.ecommerce.repositories;

import com.apis.ecommerce.entities.DiscountCoupon;
import com.apis.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountCouponsRepository extends JpaRepository<DiscountCoupon, Long> {

    @Query("SELECT d FROM DiscountCoupon d WHERE d.code = ?1")
    Optional<DiscountCoupon> findByCode(String code);
}

package com.bishal.repository;

import com.bishal.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {





    @Query("SELECT p FROM Product p " +
            "WHERE (p.category.name = :category OR :category = '') " +
            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) " +
            "AND (:minDiscount IS NULL OR p.discountPresent >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC")
    
    public List<Product> filterProducts(@RequestParam("category") String category,
                                        @RequestParam("minPrice") Integer minPrice,
                                        @RequestParam("maxPrice") Integer maxPrice,
                                        @RequestParam("minDiscount") Integer minDiscount,
                                        @RequestParam("sort") String sort );
}

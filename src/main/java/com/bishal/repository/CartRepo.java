package com.bishal.repository;

import com.bishal.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface CartRepo extends JpaRepository<Cart,Long> {


    @Query("SELECT  c from Cart c WHERE c.user.id =:userId")
    public Cart findByUserId(@RequestParam("userId") Long userId);
}

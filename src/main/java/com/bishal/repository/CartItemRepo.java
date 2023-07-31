package com.bishal.repository;

import com.bishal.model.Cart;
import com.bishal.model.CartItem;
import com.bishal.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface CartItemRepo extends JpaRepository<CartItem,Long> {


    @Query("SELECT ci FROM CartItem ci WHERE ci.cart=:cart AND ci.product=:product AND ci.size =:size and ci.userId =:userId")
    public CartItem isCartItemExist(@RequestParam("cart")Cart cart,
                                    @RequestParam("product")Product product,
                                    @RequestParam("size")String size,
                                    @RequestParam("userId")Long userId);
}

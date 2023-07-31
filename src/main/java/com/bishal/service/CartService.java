package com.bishal.service;

import com.bishal.exception.ProductException;
import com.bishal.model.Cart;
import com.bishal.model.User;
import com.bishal.request.AddItemRequest;

public interface CartService {

    public Cart createCart(User user);
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
    public Cart findUserCart(Long userId);
}

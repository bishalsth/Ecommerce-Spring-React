package com.bishal.service;

import com.bishal.exception.CartItemException;
import com.bishal.exception.UserException;
import com.bishal.model.Cart;
import com.bishal.model.CartItem;
import com.bishal.model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws CartItemException, UserException;
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
    public void removeCartItem(Long userId,Long cartItemId) throws CartItemException,UserException;
    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}

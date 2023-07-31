package com.bishal.service;

import com.bishal.exception.CartItemException;
import com.bishal.exception.UserException;
import com.bishal.model.Cart;
import com.bishal.model.CartItem;
import com.bishal.model.Product;
import com.bishal.model.User;
import com.bishal.repository.CartItemRepo;
import com.bishal.repository.CartRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService{

    private final CartItemRepo cartItemRepo;
    private final UserService userService;
    private final CartRepo cartRepo;

    public CartItemServiceImpl(CartItemRepo cartItemRepo, UserService userService, CartRepo cartRepo) {
        this.cartItemRepo = cartItemRepo;
        this.userService = userService;

        this.cartRepo = cartRepo;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {

        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()* cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());

        CartItem savedCartItem = cartItemRepo.save(cartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {

        CartItem item = findCartItemById(id);
        User user = userService.findUserById(item.getUserId());

        if(user.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getProduct().getPrice()*item.getQuantity());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());

        }



        return  cartItemRepo.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        CartItem cartItem = cartItemRepo.isCartItemExist(cart,product,size,userId);

        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem item = findCartItemById(cartItemId);
        User user = userService.findUserById(item.getUserId());

        User reqUser = userService.findUserById(userId);
        if(user.getId().equals(reqUser.getId())){
            cartItemRepo.deleteById(cartItemId);
        }else{
            throw new UserException("you cannot remove another users item");
        }

    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt = cartItemRepo.findById(cartItemId);
        if(opt.isPresent()){
            return opt.get();
        }

        throw new CartItemException("cartItem not found with id:" + cartItemId);
    }
}

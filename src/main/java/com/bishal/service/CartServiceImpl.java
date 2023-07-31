package com.bishal.service;

import com.bishal.exception.ProductException;
import com.bishal.model.Cart;
import com.bishal.model.CartItem;
import com.bishal.model.Product;
import com.bishal.model.User;
import com.bishal.repository.CartRepo;
import com.bishal.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepo cartRepo;
    private final CartItemService cartItemService;
    private final ProductService productService;

    public CartServiceImpl(CartRepo cartRepo, CartItemService cartItemService, ProductService productService) {
        this.cartRepo = cartRepo;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);

        return cartRepo.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart = cartRepo.findByUserId(userId);
        Product product= productService.findProductById(req.getProductId());
        CartItem isPresent = cartItemService.isCartItemExist(cart,product, req.getSize(), userId);
        if(isPresent == null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);
            int price = req.getQuantity()*product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());

            CartItem createdCardItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCardItem);
        }

        return "Items add to cart ";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepo.findByUserId(userId);
        int totalprice=0;
        int totalDiscountedPrice= 0;
        int totalItem =0;
        for(CartItem cartItem :cart.getCartItems()){
            totalprice =totalprice +cartItem.getPrice();
            totalDiscountedPrice = totalDiscountedPrice+cartItem.getDiscountedPrice();
            totalItem =totalItem+cartItem.getQuantity();
        }

        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalPrice(totalprice);
        cart.setTotalItem(totalItem);
        cart.setDiscounte(totalprice-totalDiscountedPrice);


        return cartRepo.save(cart);
    }
}

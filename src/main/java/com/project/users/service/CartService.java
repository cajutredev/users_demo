package com.project.users.service;

import com.project.users.dto.CartItemDto;
import com.project.users.entity.CartItem;
import com.project.users.entity.Product;
import com.project.users.entity.User;
import com.project.users.repository.CartItemRepository;
import com.project.users.repository.ProductRepository;
import com.project.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public boolean addToCart(String userId, CartItemDto cartItemDto) {

        Optional<Product> productOpt = productRepository.findById(cartItemDto.getProductId());

        if (productOpt.isEmpty()) {
            return false;
        }

        Product product = productOpt.get();
        if (product.getStock() < cartItemDto.getQuantity()) {
            return false;
        }

        Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));

        if (userOpt.isEmpty()) {
            return false;
        }

        User user = userOpt.get();
        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);

        if (existingCartItem != null) {
           existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemDto.getQuantity());

           BigDecimal newPrice = product.getProductPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity()));
           existingCartItem.setPrice(product.getProductPrice());
           cartItemRepository.save(existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setPrice(product.getProductPrice().multiply(BigDecimal.valueOf(cartItemDto.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        return true;
    }


    public boolean deleteItemFromCart(String userId, Long productId) {

        Optional<Product> productOpt = productRepository.findById(productId);
        Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));

        if (productOpt.isPresent() && userOpt.isPresent()) {
            cartItemRepository.deleteByUserAndProduct(userOpt.get(), productOpt.get());
        }
        return false;
    }

    public List<CartItem> getCartItems(String userId) {
        return userRepository.findById(Long.valueOf(userId))
                .map(cartItemRepository::findByUser)
                .orElseGet(List::of);
    }
}

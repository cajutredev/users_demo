package com.project.users.controller;

import com.project.users.dto.CartItemDto;
import com.project.users.entity.CartItem;
import com.project.users.service.CartService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@NoArgsConstructor
public class CartItemController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-User-Id") String userId,
            @RequestBody CartItemDto cartItemDto) {

        if (!cartService.addToCart(userId, cartItemDto)) {
            return ResponseEntity.badRequest().body("Product out of order");
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Boolean> removeFromCart(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable Long productId) {

        boolean deleted = cartService.deleteItemFromCart(userId, productId);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<CartItem>> getCartItems(
            @RequestHeader("X-User-Id") String userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }
}

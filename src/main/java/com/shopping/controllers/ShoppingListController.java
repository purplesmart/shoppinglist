package com.shopping.controllers;

import com.shopping.model.Receipt;
import com.shopping.model.ShoppingList;
import com.shopping.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/shopping")
public class ShoppingListController {

    @Autowired
    private ShoppingService shoppingService;

    @GetMapping("/shoppingreceipt")
    public ResponseEntity<Receipt> shopping(@RequestBody @Valid ShoppingList shoppingItems) {
        try {
            return Optional
                    .ofNullable(shoppingService.getReceipt(shoppingItems))
                    .map(receipt -> ResponseEntity.ok().body(receipt))
                    .orElseGet(() -> ResponseEntity.internalServerError().build());
        }catch (Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "General error", ex);
        }
    }
}
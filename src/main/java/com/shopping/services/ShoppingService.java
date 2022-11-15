package com.shopping.services;

import com.shopping.analyzers.ShoppingAnalyzer;
import com.shopping.model.Receipt;
import com.shopping.model.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingService {

    @Autowired
    private ShoppingAnalyzer shoppingAnalyzer;

    public Receipt getReceipt(ShoppingList shoppingList){
        return shoppingAnalyzer.getReceipt(shoppingList);
    }

}

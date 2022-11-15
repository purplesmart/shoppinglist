package com.shopping.analyzers;

import com.shopping.model.*;
import com.shopping.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ShoppingAnalyzer {

    public ShoppingAnalyzer(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public ShoppingAnalyzer() {
    }

    @Autowired
    private CatalogRepository catalogRepository;

    public Receipt getReceipt(ShoppingList shoppingList) {
        return shoppingItemsToReceipt(shoppingList.groceries);
    }

    private Receipt shoppingItemsToReceipt(List<ShoppingItem> shoppingList) {
        Receipt receipt = new Receipt();
        for (int i = 0; i < shoppingList.size(); i++) {
            ShoppingItem shoppingItem = shoppingList.get(i);
            Optional<Catalog> catalog = catalogRepository.findByProductName(shoppingItem.getProductName()).stream().findFirst();
            if (!catalog.isEmpty()) {
                ReceiptRecord receiptRecord = getReceiptRecord(catalog.get(), shoppingItem);
                receipt.AddReceiptRecord(receiptRecord);
            }
        }
        return receipt;
    }

    private ReceiptRecord getReceiptRecord(Catalog catalog,ShoppingItem shoppingItem) {
        return new ReceiptRecord(
                catalog.getProductId(),
                catalog.getProductName(),
                catalog.getProductWeight(),
                shoppingItem.getQuantity(),
                catalog.getProductPrice() * shoppingItem.getQuantity());
    }
}

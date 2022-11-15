package com.shopping.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class ShoppingItem {

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonProperty
    @NotEmpty(message = "Shopping item can't be empty.")
    private String name;

    @JsonProperty
    private int quantity;

    public String getProductName() {
        return (name == null || name.isEmpty()) ? "" : name;
    }

    public int getQuantity() {
        return (quantity == 0) ? 1 : quantity;
    }
}

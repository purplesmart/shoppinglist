package com.shopping.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;

public class ReceiptRecord {

    @JsonProperty
    int product_id;
    @JsonProperty
    String product_name;
    @JsonProperty
    double product_weight;
    @JsonProperty
    int product_quantity;

    double product_price;

    @JsonProperty
    String product_price_display;

    public ReceiptRecord(int product_id, String product_name, double product_weight, int product_quantity, double product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_weight = product_weight;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
        this.product_price_display = new DecimalFormat("##.##").format(this.product_price);
    }

}

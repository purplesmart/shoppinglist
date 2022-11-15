package com.shopping.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

    @JsonProperty
    public List<ReceiptRecord> recordList;

    double totalAmount;

    @JsonProperty
    public String totalAmountDisplay;

    public Receipt(){
        recordList = new ArrayList<>();
        totalAmount = 0;
    }

    public void AddReceiptRecord(ReceiptRecord receiptRecord){
        recordList.add(receiptRecord);
        totalAmount += receiptRecord.product_price;
        this.totalAmountDisplay = new DecimalFormat("##.##").format(this.totalAmount);
    }
}

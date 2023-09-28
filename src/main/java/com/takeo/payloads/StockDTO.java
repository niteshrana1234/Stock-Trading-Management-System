package com.takeo.payloads;

import lombok.Data;

@Data
public class StockDTO {

    private String stockName;
    private double currentPrice;
    private String category;
    private int quantity;
}

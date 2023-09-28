package com.takeo.payloads;

import lombok.Data;

@Data
public class SellStockDTO {
    private int userId;
    private String stockName;
    private int quantity;
}

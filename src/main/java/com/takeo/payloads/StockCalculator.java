package com.takeo.payloads;

public class StockCalculator {

    public static double calculate(double price, int quantity) {

        return price * quantity;
    }

    public static int stockQuantity(int quantity, int quantitySold) {
        int remainQuantity = 0;
        if (quantity >= quantitySold) {
            remainQuantity = quantity - quantitySold;
            return remainQuantity;
        }
        return quantity;
    }


}

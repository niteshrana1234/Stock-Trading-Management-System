package com.takeo.service;

import com.takeo.model.Stock;
import com.takeo.payloads.SellStockDTO;
import com.takeo.payloads.StockDTO;

import java.util.List;

public interface StockService {
    public String buyStock(int userId, List<StockDTO> list);
    public String sellStock(SellStockDTO stockDTO);
    public List<Stock> viewALlStock(int userId);
}

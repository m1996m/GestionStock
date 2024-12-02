package com.GestionStock.stock.service;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Stock;
import com.GestionStock.stock.repository.StockRepository;
import com.GestionStock.stock.repository.generic.StockDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService extends GenericService<Stock,String> {
    private final StockRepository stockRepository;

    public StockService(StockDAO stockDAO, StockRepository stockRepository) {
        super(stockDAO);
        this.stockRepository = stockRepository;
    }

    public List<Stock> findStockByMagasin(Magasin magasin){
        return stockRepository.findByMagasin(magasin);
    }
}

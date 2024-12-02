package com.GestionStock.stock.controller;

import com.GestionStock.stock.dto.stock.StockDto;
import com.GestionStock.stock.dto.stock.StockMapper;
import com.GestionStock.stock.dto.stock.StockResponseDto;
import com.GestionStock.stock.generic.GenericController;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Stock;
import com.GestionStock.stock.service.MagasinService;
import com.GestionStock.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController extends GenericController<Stock, String, StockDto, StockResponseDto> {
    private final StockService stockService;
    private final StockMapper stockMapper;

    public StockController(
            GenericService<Stock, String> genericService, GenericMapper<Stock, StockDto, StockResponseDto> genericMapper,
            StockService stockService, StockMapper stockMapper
    ) {
        super(genericService, genericMapper);
        this.stockService = stockService;
        this.stockMapper = stockMapper;
    }

    @GetMapping("/magasin/{id}")
    public List<StockResponseDto> getListStockByMagasin(@PathVariable Long id){
        List<Stock> stocks = stockService.findDynamicByOneTable("magasin", "idMagasin", id);

        return stocks.stream().map(stockMapper::toResponseDto).toList();
    }
}

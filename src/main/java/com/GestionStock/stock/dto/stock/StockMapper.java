package com.GestionStock.stock.dto.stock;

import com.GestionStock.stock.dto.magasin.MagasinMapper;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Stock;
import com.GestionStock.stock.service.MagasinService;
import com.GestionStock.stock.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class StockMapper implements GenericMapper<Stock, StockDto, StockResponseDto> {
    private final MagasinService magasinService;
    private final StockService stockService;
    private final MagasinMapper magasinMapper;

    public StockMapper(MagasinService magasinService, StockService stockService, MagasinMapper magasinMapper) {
        this.magasinService = magasinService;
        this.stockService = stockService;
        this.magasinMapper = magasinMapper;
    }


    @Override
    public StockDto toDto(Stock stock) {
        return StockDto.builder()
                .idStock(stock.getIdStock())
                .name(stock.getName())
                .createdAt(stock.getCreatedAt())
                .description(stock.getDescription())
                .slug(stock.getSlug())
                .build();
    }

    @Override
    public StockResponseDto toResponseDto(Stock stock) {
        return StockResponseDto.builder()
                .idStock(stock.getIdStock())
                .name(stock.getName())
                .createdAt(stock.getCreatedAt())
                .description(stock.getDescription())
                .slug(stock.getSlug())
                .magasin(magasinMapper.toDto(stock.getMagasin()))
                .build();
    }

    @Override
    public Stock toEntity(StockDto stockDto) {
        if (stockDto.getSlug() != null){
            Stock stock = stockService.findByValue(stockDto.getSlug(), "slug");

            return stockDto.update(stockDto, stock);
        }else{
            Magasin magasin = magasinService.findById(stockDto.getIdMagasin());

            return stockDto.create(stockDto,magasin);
        }
    }
}

package com.GestionStock.stock.repository.generic;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Stock;
import org.springframework.stereotype.Repository;

@Repository
public class StockDAO extends GenericDAO<Stock> {
    public StockDAO() {
        super(Stock.class);
    }
}

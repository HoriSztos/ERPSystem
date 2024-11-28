package com.example.erpsystem.finances.service;

import com.example.erpsystem.finances.model.Sale;
import com.example.erpsystem.finances.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    @Autowired
    public SaleService(SaleRepository saleRepository){this.saleRepository = saleRepository;}

    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }
    public Sale getSaleByID(Long id){
        return saleRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Sprzedaży nie znaleziono"));
    }
    public Sale saveSale(Sale sale){
        return saleRepository.save(sale);
    }
    public void deleteSale(Long id){
        saleRepository.deleteById(id);
    }

}

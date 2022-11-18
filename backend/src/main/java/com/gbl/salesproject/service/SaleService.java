package com.gbl.salesproject.service;

import com.gbl.salesproject.model.Sale;
import com.gbl.salesproject.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public Page<Sale> findAll(String minDate, String maxDate, Pageable pageable) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate min = minDate.equals("") ? today.minusYears(1L) : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

        return saleRepository.findAll(min, max, pageable);
    }
}

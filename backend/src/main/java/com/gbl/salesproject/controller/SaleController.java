package com.gbl.salesproject.controller;

import com.gbl.salesproject.model.Sale;
import com.gbl.salesproject.service.SaleService;
import com.gbl.salesproject.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private SmsService smsService;

    @GetMapping
    public Page<Sale> findSales(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            Pageable pageable) {

        return saleService.findAll(minDate, maxDate, pageable);
    }

    @GetMapping("/{id}/sendSMS")
    public void sendSMS(@PathVariable Long id) {
        smsService.sendSms(id);
    }

}

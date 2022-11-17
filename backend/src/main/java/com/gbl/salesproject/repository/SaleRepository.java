package com.gbl.salesproject.repository;

import com.gbl.salesproject.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}

package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
    @Query(value = "SELECT s.name, count(*) FROM leads l INNER JOIN sales_rep s ON l.salesrep_id = s.id GROUP BY l.salesrep_id ORDER BY name DESC", nativeQuery = true)
    List<Object[]> getLeadsBySalesRep();
}

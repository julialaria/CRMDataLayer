package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {
    public SalesRep findByName(String name);
}

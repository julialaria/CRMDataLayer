package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {
    public Optional<SalesRep> findByName(String name);
}

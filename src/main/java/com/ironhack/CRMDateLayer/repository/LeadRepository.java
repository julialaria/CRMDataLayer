package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {

}

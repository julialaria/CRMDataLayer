package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

}

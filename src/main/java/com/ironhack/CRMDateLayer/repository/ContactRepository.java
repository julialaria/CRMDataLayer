package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    public Contact findByName(String name);

}

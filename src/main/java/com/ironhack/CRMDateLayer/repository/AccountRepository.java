package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}

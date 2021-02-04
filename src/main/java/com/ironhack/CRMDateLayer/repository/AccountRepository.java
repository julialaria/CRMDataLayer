package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


//    ----------------------------------------------------------------------------
//    ---------------------------------Employee Count states----------------------------
//    ----------------------------------------------------------------------------

    //    The mean employeeCount
    @Query(value = "SELECT AVG(employeeCount) FROM account", nativeQuery = true)
    public List<Object[]> meanEmployeeCount();

    //    The median employeeCount
    @Query(value = "SELECT AVG(employee_count) as Median FROM (SELECT @rowindex\\:=@rowindex + 1 AS rowindex, employee_count AS employee_count FROM account  ORDER BY employee_count) AS o WHERE a.rowindex IN (FLOOR(@rowindex / 2), CEIL(@rowindex / 2))", nativeQuery = true)
    public List<Object[]> medianEmployeeCount();

    //    The maximum employeeCount of products order
    @Query(value = "SELECT MAX(employeeCount) FROM account", nativeQuery = true)
    public List<Object[]> maxEmployeeCount();

    //    The maximum employeeCount of products order
    @Query(value = "SELECT MIN(employeeCount) FROM account", nativeQuery = true)
    public List<Object[]> minEmployeeCount();


    @Modifying
    @Transactional
    @Query(value = "SET @rowindex \\:=-1;", nativeQuery = true)
    void setRowIndex();
}

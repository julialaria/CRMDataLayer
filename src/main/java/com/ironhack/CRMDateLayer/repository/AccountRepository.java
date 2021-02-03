package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


//    ----------------------------------------------------------------------------
//    ---------------------------------Employee Count states----------------------------
//    ----------------------------------------------------------------------------

    //    The mean employeeCount
    @Query(value = "SELECT AVG(employeeCount) FROM account", nativeQuery = true)
    public List<Object[]> meanQuantityOfOrders();

    //    The median employeeCount
    @Query(value = "SET @rowindex := -1" +
            "SELECT" +
            "   AVG(employee_count) as Median" +
            "FROM" +
            "   (SELECT @rowindex:=@rowindex + 1 AS rowindex," +
            "           employee_count AS employee_count" +
            "    FROM account " +
            "    ORDER BY employee_count) AS o" +
            "WHERE" +
            "a.rowindex IN (FLOOR(@rowindex / 2), CEIL(@rowindex / 2))", nativeQuery = true)
    public List<Object[]> medianQuantityOfOrders();

    //    The maximum employeeCount of products order
    @Query(value = "SELECT MAX(employeeCount) FROM account", nativeQuery = true)
    public List<Object[]> maxQuantityOfOrders();

    //    The maximum employeeCount of products order
    @Query(value = "SELECT MIN(employeeCount) FROM account", nativeQuery = true)
    public List<Object[]> minQuantityOfOrders();

    @Query(value = "SELECT () FROM account", nativeQuery = true)
    public List<Object[]> meanQuantityOfOpportunities();


}

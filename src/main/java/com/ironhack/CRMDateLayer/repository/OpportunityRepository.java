package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
    @Query(value = "SELECT s.name , count(*) FROM opportunity o INNER JOIN sales_rep s ON o.salesrep_id = s.id GROUP BY o.salesrep_id", nativeQuery = true)
    List<Object[]> countOpportunitiesBySalesRep();

    @Query(value = "SELECT s.name , count(*) FROM opportunity o INNER JOIN sales_rep s ON o.salesrep_id = s.id where status = 'CLOSED_WON' GROUP BY o.salesrep_id", nativeQuery = true)
    List<Object[]> countOpportunitiesBySalesRepClosedWon();

    @Query(value = "SELECT s.name , count(*) FROM opportunity o INNER JOIN sales_rep s ON o.salesrep_id = s.id where status = 'CLOSED_LOST' GROUP BY o.salesrep_id", nativeQuery = true)
    List<Object[]> countOpportunitiesBySalesRepClosedLost();

    @Query(value = "SELECT s.name , count(*) FROM opportunity o INNER JOIN sales_rep s ON o.salesrep_id = s.id where status = 'OPEN' GROUP BY o.salesrep_id", nativeQuery = true)
    List<Object[]> countOpportunitiesBySalesRepOpen();

}

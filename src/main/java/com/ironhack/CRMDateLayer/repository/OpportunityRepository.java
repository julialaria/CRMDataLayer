package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
//    ----------------------------------------------------------------------------
//    ---------------------------------By SalesRep--------------------------------
//    ----------------------------------------------------------------------------

    //    A count of all Opportunities by SalesRep
    @Query(value = "SELECT s.name , count(*) FROM opportunity o INNER JOIN sales_rep s ON o.salesrep_id = s.id GROUP BY o.salesrep_id", nativeQuery = true)
    List<Object[]> countOpportunitiesBySalesRep();

//    A count of all CLOSED_WON Opportunities by SalesRep
    @Query(value = "SELECT s.name , count(*) FROM opportunity o INNER JOIN sales_rep s ON o.salesrep_id = s.id where status = 'CLOSED_WON' GROUP BY o.salesrep_id", nativeQuery = true)
    List<Object[]> countOpportunitiesBySalesRepClosedWon();

//    A count of all CLOSED_LOST Opportunities by SalesRep
    @Query(value = "SELECT s.name , count(*) FROM opportunity o INNER JOIN sales_rep s ON o.salesrep_id = s.id where status = 'CLOSED_LOST' GROUP BY o.salesrep_id", nativeQuery = true)
    List<Object[]> countOpportunitiesBySalesRepClosedLost();

//    A count of all OPEN Opportunities by SalesRep
    @Query(value = "SELECT s.name , count(*) FROM opportunity o INNER JOIN sales_rep s ON o.salesrep_id = s.id where status = 'OPEN' GROUP BY o.salesrep_id", nativeQuery = true)
    List<Object[]> countOpportunitiesBySalesRepOpen();
    
    
    
    
//    ----------------------------------------------------------------------------
//    ---------------------------------By Product --------------------------------
//    ----------------------------------------------------------------------------

//    A count of all Opportunities by product
    @Query(value = "SELECT product, count(*) FROM opportunity GROUP BY product", nativeQuery = true)
    List<Object[]> countOpportunitiesByProduct();

//    A count of all CLOSED_WON Opportunities by product
    @Query(value = "SELECT product, count(*) FROM opportunity WHERE status = 'CLOSED_WON' GROUP BY product", nativeQuery = true)
    List<Object[]> countOpportunitiesByProductClosedWon();

//    A count of all CLOSED_LOST Opportunities by product
    @Query(value = "SELECT product, count(*) FROM opportunity WHERE status = 'CLOSED_LOST' GROUP BY product", nativeQuery = true)
    List<Object[]> countOpportunitiesByProductClosedLost();

//    A count of all OPEN Opportunities by product
    @Query(value = "SELECT product, count(*) FROM opportunity WHERE status = 'OPEN' GROUP BY product", nativeQuery = true)
    List<Object[]> countOpportunitiesByProductOpen();

//    ----------------------------------------------------------------------------
//    ---------------------------------By Country --------------------------------
//    ----------------------------------------------------------------------------

//    A count of all Opportunities by country
    @Query(value = "SELECT a.country, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id GROUP BY a.country", nativeQuery = true)
    List<Object[]> countOpportunitiesByCountry();

//    A count of all CLOSED_WON Opportunities by country
    @Query(value = "SELECT a.country, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id  WHERE o.status = 'CLOSED_WON' GROUP BY a.country", nativeQuery = true)
    List<Object[]> countOpportunitiesByCountryClosedWon();

//    A count of all CLOSED_LOST Opportunities by country
    @Query(value = "SELECT a.country, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id  WHERE o.status = 'CLOSED_LOST' GROUP BY a.country", nativeQuery = true)
    List<Object[]> countOpportunitiesByCountryClosedLost();

//    A count of all OPEN Opportunities by country
    @Query(value = "SELECT a.country, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id  WHERE o.status = 'OPEN' GROUP BY a.country", nativeQuery = true)
    List<Object[]> countOpportunitiesByCountryOpen();

//    ----------------------------------------------------------------------------
//    ---------------------------------By City -----------------------------------
//    ----------------------------------------------------------------------------

//    A count of all Opportunities by city

    @Query(value = "SELECT a.city, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id GROUP BY a.city", nativeQuery = true)
    List<Object[]> countOpportunitiesByCity();

//    A count of all CLOSED_WON Opportunities by city
    @Query(value = "SELECT a.city, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id WHERE o.status = 'CLOSED_WON' GROUP BY a.city;\n", nativeQuery = true)
    List<Object[]> countOpportunitiesByCityClosedWon();

//    A count of all CLOSED_LOST Opportunities by city
    @Query(value = "SELECT a.city, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id WHERE o.status = 'CLOSED_LOST' GROUP BY a.city;\n", nativeQuery = true)
    List<Object[]> countOpportunitiesByCityClosedLost();

//    A count of all OPEN Opportunities by city
    @Query(value = "SELECT a.city, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id WHERE o.status = 'CLOSED_OPEN' GROUP BY a.city;\n", nativeQuery = true)
    List<Object[]> countOpportunitiesByCityOpen();


//    ----------------------------------------------------------------------------
//    ---------------------------------By Industry--------------------------------
//    ----------------------------------------------------------------------------

//    A count of all Opportunities by industry
    @Query(value = "SELECT a.industry, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id GROUP BY a.industry", nativeQuery = true)
    public List<Object[]> countOpportunitiesByIndustry();

//    A count of all CLOSED_WON Opportunities by industry
    @Query(value = "SELECT a.industry, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id WHERE o.status = 'CLOSED_WON' GROUP BY a.industry", nativeQuery = true)
    public List<Object[]> countOpportunitiesByIndustryClosedWon();

//    A count of all CLOSED_LOST Opportunities by industry
    @Query(value = "SELECT a.industry, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id WHERE o.status = 'CLOSED_LOST' GROUP BY a.industry", nativeQuery = true)
    public List<Object[]> countOpportunitiesByIndustryClosedLost();

//    A count of all OPEN Opportunities by industry
    @Query(value = "SELECT a.industry, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id WHERE o.status = 'OPEN' GROUP BY a.industry", nativeQuery = true)
    public List<Object[]> countOpportunitiesByIndustryOpen();


//    ----------------------------------------------------------------------------
//    -----------------------------------------------------
//    ----------------------------------------------------------------------------

    //    The mean quantity of products order
    @Query(value = "SELECT AVG(quantity) FROM opportunity", nativeQuery = true)
    public List<Object[]> meanQuantityOfOrders();

    //    The median quantity of products order
    @Query(value = "SET @rowindex :=-1;" +
            "SELECT" +
            "   AVG(quantity) as Median" +
            "FROM" +
            "   (SELECT @rowindex:=@rowindex + 1 AS rowindex," +
            "           quantity AS quantity" +
            "    FROM opportunity " +
            "    ORDER BY quantity) AS o" +
            "WHERE" +
            "o.rowindex IN (FLOOR(@rowindex / 2), CEIL(@rowindex / 2))", nativeQuery = true)
    public List<Object[]> medianQuantityOfOrders();

    //    The maximum quantity of products order
    @Query(value = "SELECT MAX(quantity) FROM opportunity", nativeQuery = true)
    public List<Object[]> maxQuantityOfOrders();

    //    The maximum quantity of products order
    @Query(value = "SELECT MIN(quantity) FROM opportunity", nativeQuery = true)
    public List<Object[]> minQuantityOfOrders();

//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------

    @Query(value = "select AVG(counts) from(select account_id, COUNT(*) as counts from opportunity GROUP BY account_id) as table1", nativeQuery = true)
    public List<Object[]> averageOpportunitiesInAccount();

    @Query(value = "Select MAX(counts) from (select COUNT(*) as counts from opportunity GROUP BY account_id) as table1", nativeQuery = true)
    public List<Object[]> maxOpportunitiesInAccount();

    @Query(value = "Select MIN(counts) from (select COUNT(*) as counts from opportunity GROUP BY account_id) as table1", nativeQuery = true)
    public List<Object[]> minOpportunitiesInAccount();

}

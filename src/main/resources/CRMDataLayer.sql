DROP SCHEMA crmdatalayer;
CREATE SCHEMA crmdatalayer;
USE crmdatalayer;

CREATE TABLE sales_rep(
id INT AUTO_INCREMENT NOT NULL, 
name VARCHAR(255), 
PRIMARY KEY(id)
);

CREATE TABLE leads(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
company_name VARCHAR(255), 
email VARCHAR(255), 
phone_number VARCHAR(255), 
salesrep_id INT, 
PRIMARY KEY (id), 
FOREIGN KEY (salesrep_id) REFERENCES sales_rep(id)
);

CREATE TABLE account (
  id INT NOT NULL AUTO_INCREMENT,
  city VARCHAR(255),
  country VARCHAR(255) ,
  employee_count INT,
  industry VARCHAR(255) ,
  PRIMARY KEY (id)
);


CREATE TABLE contact (
  id_contact INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255)  ,
  company_name VARCHAR(255)  ,
  email VARCHAR(255)  ,
  phone_number VARCHAR(255)  ,
  account_id INT  ,
  PRIMARY KEY (id_contact),
  FOREIGN KEY (account_id) REFERENCES account(id)
  );


CREATE TABLE opportunity (
  id int NOT NULL AUTO_INCREMENT,
  product VARCHAR(255)  ,
  quantity int  ,
  status VARCHAR(255)  ,
  account_id int  ,
  contact_id int  ,
  salesrep_id int  ,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES account(id),
  FOREIGN KEY (salesrep_id) REFERENCES sales_rep (id),
  FOREIGN KEY (contact_id) REFERENCES contact (id_contact)
) ;

INSERT INTO sales_rep(name) VALUES
('JoséMari'), 
('Juanito'), 
('Luisa'), 
('Pepito');

INSERT INTO leads(name, company_name, email, phone_number, salesrep_id) VALUES
('Javier','Airbus', 'javi@airbus.com', '677518326', 1), 
('Mayte', 'Boeing','mayte@gmail.com', 666555222, 3), 
('Rodrigo', 'Oxford','rodri@hotmail.com',666111444 ,1 ),
('Andres','Pfizer', 'andres@hotmail.com',666115444 ,2 ),
('Luis', 'Moderna','luis@hotmail.com',656115454 ,4 ),
('Minerva', 'Mahou','min@hotmail.com',655269454 ,2 );

INSERT INTO account(city, country, employee_count, industry) VALUES
('Madrid', 'España', 150, 'PRODUCE'), 
('Valencia', 'España', 300, 'MANUFACTURING'),
('Pekin', 'China', 1800, 'ECOMMERCE'),
('Lousiana', 'EEUU', 1655, 'MEDICAL'),
('San Diego', 'EEUU', 25, 'MEDICAL'),
('Moscú', 'Rusia', 1500, 'OTHER');

INSERT INTO contact(name, company_name, email, phone_number, account_id) VALUES
('Javier','Airbus', 'javi@airbus.com', '677518326', 1), 
('Mayte', 'Boeing','mayte@gmail.com', 666555222, 3), 
('Rodrigo', 'Oxford','rodri@hotmail.com',666111444 ,4 ),
('Andres','Pfizer', 'andres@hotmail.com',666115444, 5 ),
('Luis', 'Moderna','luis@hotmail.com',656115454 ,6 ),
('Minerva', 'Mahou','min@hotmail.com',655269454 ,2 );

INSERT INTO opportunity( product, quantity, status, account_id, contact_id, salesrep_id) VALUES
('HYBRID', 5, 'OPEN', 1, 1 , 1), 
('FLATBED', 20, 'OPEN', 2, 3 , 3), 
('HYBRID', 50, 'CLOSED_WON', 3, 4 , 1), 
('BOX', 25, 'CLOSED_LOST', 4, 5 , 2), 
('BOX', 15, 'CLOSED_WON', 4, 6 , 4), 
('FLATBED', 53, 'OPEN', 6, 2, 2);

-- By SalesRep
-- A count of Leads by SalesRep can be displayed by typing "Report Lead by SalesRep"
SELECT l.salesrep_id, s.name AS salesrep_name, count(*) FROM leads l INNER JOIN sales_rep s ON l.salesrep_id = s.id GROUP BY l.salesrep_id;
-- A count of all Opportunities by SalesRep can be displayed by typing "Report Opportunity by SalesRep"
SELECT o.salesrep_id, s.name , count(*) FROM opportunity o INNER JOIN sales_rep s ON o.salesrep_id = s.id GROUP BY o.salesrep_id;
-- A count of all CLOSED_WON Opportunities by SalesRep can be displayed by typing "Report CLOSED-WON by SalesRep"
SELECT o.salesrep_id, s.name , count(*) FROM opportunity o INNER JOIN sales_rep s ON o.salesrep_id = s.id  WHERE o.status = 'CLOSED_WON' GROUP BY o.salesrep_id ;
-- A count of all CLOSED_LOST Opportunities by SalesRep can be displayed by typing "Report CLOSED-LOST by SalesRep"
SELECT o.salesrep_id, s.name , count(*) FROM opportunity o INNER JOIN sales_rep s ON o.salesrep_id = s.id  WHERE o.status = 'CLOSED_LOST' GROUP BY o.salesrep_id ;
-- A count of all OPEN Opportunities by SalesRep can be displayed by typing "Report OPEN by SalesRep"
SELECT o.salesrep_id, s.name , count(*) FROM opportunity o INNER JOIN sales_rep s ON o.salesrep_id = s.id  WHERE o.status = 'OPEN' GROUP BY o.salesrep_id ;

-- By Product

-- A count of Leads by product can be displayed by typing "Report Lead by Product"
-- SELECT o.product, count(*) FROM leads l INNER JOIN contact c ON c.phone_number=l.   -- !!! REVISAR!
-- A count of all Opportunities by product can be displayed by typing "Report Opportunity by product"
SELECT product, count(*) FROM opportunity GROUP BY product;
-- A count of all CLOSED_WON Opportunities by product can be displayed by typing "Report CLOSED-WON by product"
SELECT product, count(*) FROM opportunity WHERE status = 'CLOSED_WON' GROUP BY product;
-- A count of all CLOSED_LOST Opportunities by product can be displayed by typing "Report CLOSED-LOST by product"
SELECT product, count(*) FROM opportunity WHERE status = 'CLOSED_LOST' GROUP BY product;
-- A count of all OPEN Opportunities by product can be displayed by typing "Report OPEN by product"
SELECT product, count(*) FROM opportunity WHERE status = 'OPEN' GROUP BY product;

-- --------------------------------------------------------------------------------------------------------
-- By country
-- --------------------------------------------------------------------------------------------------------

-- A count of Leads by country can be displayed by typing "Report Lead by Country"

-- A count of all Opportunities by country can be displayed by typing "Report Opportunity by Country"
SELECT a.country, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id GROUP BY a.country;
-- A count of all CLOSED_WON Opportunities by country can be displayed by typing "Report CLOSED-WON by Country"
SELECT a.country, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id  WHERE o.status = 'CLOSED_WON' GROUP BY a.country;
-- A count of all CLOSED_LOST Opportunities by country can be displayed by typing "Report CLOSED-LOST by Country"
SELECT a.country, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id  WHERE o.status = 'CLOSED_LOST' GROUP BY a.country;
-- A count of all OPEN Opportunities by country can be displayed by typing "Report OPEN by Country"
SELECT a.country, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id  WHERE o.status = 'OPEN' GROUP BY a.country;

-- --------------------------------------------------------------------------------------------------------
-- By City
-- --------------------------------------------------------------------------------------------------------

-- A count of Leads by city can be displayed by typing "Report Lead by City"

-- A count of all Opportunities by city can be displayed by typing "Report Opportunity by City"
SELECT a.city, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id GROUP BY a.city;
-- A count of all CLOSED_WON Opportunities by city can be displayed by typing "Report CLOSED-WON by City"
SELECT a.city, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id WHERE o.status = 'CLOSED_WON' GROUP BY a.city;
-- A count of all CLOSED_LOST Opportunities by city can be displayed by typing "Report CLOSED-LOST by City"
SELECT a.city, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id WHERE o.status = 'CLOSED_LOST' GROUP BY a.city;
-- A count of all OPEN Opportunities by city can be displayed by typing "Report OPEN by City"
SELECT a.city, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id WHERE o.status = 'OPEN' GROUP BY a.city;
-- --------------------------------------------------------------------------------------------------------
-- By Industry
-- --------------------------------------------------------------------------------------------------------

-- A count of Leads by industry can be displayed by typing "Report Lead by Industry"

-- A count of all Opportunities by industry can be displayed by typing "Report Opportunity by Industry"
SELECT a.industry, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id GROUP BY a.industry;
-- A count of all CLOSED_WON Opportunities by industry can be displayed by typing "Report CLOSED-WON by Industry"
SELECT a.industry, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id WHERE o.status = 'CLOSED_WON' GROUP BY a.industry;
-- A count of all CLOSED_LOST Opportunities by industry can be displayed by typing "Report CLOSED-LOST by Industry"
SELECT a.industry, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id WHERE o.status = 'CLOSED_LOST' GROUP BY a.industry;
-- A count of all OPEN Opportunities by industry can be displayed by typing "Report OPEN by Industry"
SELECT a.industry, count(*) FROM opportunity o JOIN account a ON o.account_id= a.id WHERE o.status = 'OPEN' GROUP BY a.industry;
-- --------------------------------------------------------------------------------------------------------
-- EmployeeCount States
-- --------------------------------------------------------------------------------------------------------

-- The mean employeeCount can be displayed by typing "Mean EmployeeCount"
SELECT AVG(employee_count) FROM account;
-- The median employeeCount can be displayed by typing "Median EmployeeCount"
SET @rowindex := -1;
SELECT
   AVG(employee_count) as Median 
FROM
   (SELECT @rowindex:=@rowindex + 1 AS rowindex,
           employee_count AS employee_count
    FROM account 
    ORDER BY employee_count) AS e
WHERE
e.rowindex IN (FLOOR(@rowindex / 2), CEIL(@rowindex / 2));
-- The maximum employeeCount can be displayed by typing "Max EmployeeCount"
SELECT MAX(employee_count) FROM account;
-- The minimum employeeCount can be displayed by typing "Min EmployeeCount"
SELECT MIN(employee_count) FROM account;
-- --------------------------------------------------------------------------------------------------------
-- Quantity States
-- --------------------------------------------------------------------------------------------------------

-- The mean quanitity of products order can be displayed by typing "Mean Quantity"
SELECT AVG(quantity) FROM opportunity;
-- The median quanitity of products order can be displayed by typing "Median Quantity"
SET @rowindex := -1;
SELECT
   AVG(quantity) as Median 
FROM
   (SELECT @rowindex:=@rowindex + 1 AS rowindex,
           quantity AS quantity
    FROM opportunity 
    ORDER BY quantity) AS o
WHERE
o.rowindex IN (FLOOR(@rowindex / 2), CEIL(@rowindex / 2));

-- The maximum quanitity of products order can be displayed by typing "Max Quantity"
SELECT MAX(quantity) FROM opportunity;
-- The minimum quanitity of products order can be displayed by typing "Min Quantity"
SELECT MIN(quantity) FROM opportunity;

-- --------------------------------------------------------------------------------------------------------
-- Opportunity States
-- --------------------------------------------------------------------------------------------------------

-- The mean number of Opportunities associated with an Account can be displayed by typing "Mean Opps per Account"

-- The median number of Opportunities associated with an Account can be displayed by typing "Median Opps per Account"

-- The maximum number of Opportunities associated with an Account can be displayed by typing "Max Opps per Account"

-- The minimum number of Opportunities associated with an Account can be displayed by typing "Min Opps per Account"
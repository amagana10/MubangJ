-- 2.1
-- Task – Select all records from the Employee table.

SELECT * FROM EMPLOYEE;
/
-- Task – Select all records from the Employee table where last name is King.

SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';
/
-- Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.

SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;
/

--2.2
-- Task – Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM ALBUM ORDER BY TITLE DESC;
/
-- Task – Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY ASC;
/

--2.3
-- Task – Insert two new records into Genre table 
INSERT INTO GENRE (GENREID, NAME) VALUES (26, 'Espionage');
/
INSERT INTO GENRE (GENREID, NAME) VALUES (27, 'Horror');
/

-- Task – Insert two new records into Employee table
INSERT INTO EMPLOYEE VALUES (9, 'Bourne', 'Jason', 'Spy', 1, '09-JAN-78', '01-NOV-03', '922 7 ST NW', 'ORLANDO', 'FL', 'UNITED STATES', '32780', '+1 (321) 555-5555', '+1 (321) 555-3333', 'jason@chinookcorp.com');
/
INSERT INTO EMPLOYEE VALUES (10, 'Stark', 'Tony', 'Engineer', 6, '10-MAR-79', '02-JUN-02', '921 7 ST NW', 'Mountain View', 'CA', 'UNITED STATES', '94039', '+1 (650) 444-4444','+1 (650) 444-2222', 'tony@chinookcorp.com');
/

-- Task – Insert two new records into Customer table
INSERT INTO CUSTOMER VALUES (60, 'Potts', 'Pepper', 'Stark Industries', '890 Fifth Avenue', 'New York City', 'NY', 'USA', '10001', '+1 (212) 555-1111', '+1 (212) 878-9090', 'pepper@chinookcorp.com', 4);
/
INSERT INTO CUSTOMER VALUES (61, 'Howard', 'Terrence', 'Stark Industries', '890 Fifth Avenue', 'New York City', 'NY', 'USA', '10001', '+1 (212) 999-8888','+1 (212) 545-1212', 'howard@chinookcorp.com', 3);
/

--2.4

-- Task – Update Aaron Mitchell in Customer table to Robert Walter

UPDATE CUSTOMER SET FIRSTNAME = 'Robert', LASTNAME = 'Walker' WHERE CUSTOMERID = 32;
/
-- Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”	

UPDATE ARTIST SET NAME = 'CCR' WHERE ARTISTID = 76;
/

--2.5
-- Task – Select all invoices with a billing address like “T%” 

SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';
/

--2.6

-- Task – Select all invoices that have a total between 15 and 50
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;
/

-- Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';
/

--2.7
-- Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).

-- delete all invoiceline's that belong to customer (Robert Walker)

SELECT l.INVOICELINEID, l.INVOICEID FROM INVOICELINE l
WHERE l.INVOICEID 
IN (SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID = 32);
/

DELETE FROM INVOICELINE l
WHERE l.INVOICEID 
IN (SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID = 32);
/

-- delete all invoice's that belong to customer (Robert Walker)
SELECT * FROM INVOICE WHERE CUSTOMERID = 32;
/

DELETE FROM INVOICE WHERE CUSTOMERID = 32;
/

-- delete customer (Robert Walker)

SELECT * FROM CUSTOMER WHERE CUSTOMERID = 32;
/

DELETE FROM CUSTOMER WHERE CUSTOMERID = 32;
/

-- 3.1
--Task – Create a function that returns the current time.

CREATE OR REPLACE FUNCTION cur_time
    RETURN TIMESTAMP
    IS timee TIMESTAMP;
    
    BEGIN
        SELECT CURRENT_TIMESTAMP
        INTO timee
        FROM DUAL;
        
        RETURN timee;
    END;
/

SELECT cur_time() FROM DUAL;
/

-- Task – create a function that returns the length of name in MEDIATYPE table

CREATE OR REPLACE FUNCTION get_MEDIA_NAME_LEN 
    RETURN NUMBER
    IS len NUMBER;
    BEGIN
        SELECT MAX(LENGTH(NAME))
        INTO len
        FROM MEDIATYPE;
        RETURN(len);
    END;
/

SELECT get_MEDIA_NAME_LEN() AS LONGEST_MEDIA_NAME FROM dual;
/

SELECT LENGTH(NAME) FROM MEDIATYPE;
/

-- 3.2
-- Task – Create a function that returns the average total of all invoices 

CREATE OR REPLACE FUNCTION AVG_INVOICES
    RETURN NUMBER
    IS avgInv NUMBER(10,2);
    BEGIN
        SELECT AVG(TOTAL)
        INTO avgInv
        FROM INVOICE;
        RETURN avgInv;
    END;
/

SELECT AVG_INVOICES() FROM DUAL;
/

-- Task – Create a function that returns the most expensive track

CREATE OR REPLACE FUNCTION PRICEST_TRACK
    RETURN NUMBER
    IS pricest NUMBER;
    BEGIN
        SELECT MAX(UNITPRICE)
        INTO pricest
        FROM TRACK;
        RETURN pricest;
    END;
/
    
SELECT PRICEST_TRACK() FROM DUAL;
/

-- 3.3
-- Task – Create a function that returns the average price of invoiceline items in the invoiceline table

CREATE OR REPLACE FUNCTION AVG_INVOICELINE
    RETURN NUMBER
    IS avgILine NUMBER(10,2);
    BEGIN
        SELECT AVG(UNITPRICE)
        INTO avgILine
        FROM INVOICELINE;
        RETURN avgILine;
    END;
/

SELECT AVG_INVOICELINE() FROM DUAL;    
/

--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.

    
SELECT * FROM EMPLOYEE WHERE BIRTHDATE > TO_DATE('1968', 'yyyy');
/

DROP TYPE EmpDateFiltered_row;
DROP TYPE EmpDateFiltered_tab;

CREATE TYPE EmpDateFiltered_row AS OBJECT (
   EMPLOYEEID NUMBER,
   LASTNAME VARCHAR2(20),
   FIRSTNAME VARCHAR2(20),
   TITLE VARCHAR2(30),
   REPORTSTO NUMBER,
   BIRTHDATE DATE,
   HIREDATE DATE,
   ADDRESS VARCHAR2(70),
   CITY VARCHAR2(40),
   STATE VARCHAR2(40),
   COUNTRY VARCHAR2(40),
   POSTALCODE VARCHAR2(10),
   PHONE VARCHAR2(24),
   FAX VARCHAR2(24),
   EMAIL VARCHAR2(60)   
);
/

CREATE TYPE EmpDateFiltered_tab AS TABLE OF EmpDateFiltered_row;
/

CREATE OR REPLACE FUNCTION BORN_AFTER
    RETURN EmpDateFiltered_tab PIPELINED 
    IS
        out_emp_row EmpDateFiltered_row;
        CURSOR cur IS
            SELECT * FROM EMPLOYEE 
            WHERE BIRTHDATE > TO_DATE('1968', 'yyyy');
        in_emp_row cur%ROWTYPE;
BEGIN
    OPEN cur;
    LOOP
        FETCH cur INTO in_emp_row;
        EXIT WHEN cur%NOTFOUND;
        out_emp_row := EmpDateFiltered_row(
        in_emp_row.EMPLOYEEID, 
        in_emp_row.LASTNAME, 
        in_emp_row.FIRSTNAME, 
        in_emp_row.TITLE, 
        in_emp_row.REPORTSTO,
        in_emp_row.BIRTHDATE,
        in_emp_row.HIREDATE,
        in_emp_row.ADDRESS,
        in_emp_row.CITY,
        in_emp_row.STATE,
        in_emp_row.COUNTRY,
        in_emp_row.POSTALCODE,
        in_emp_row.PHONE,
        in_emp_row.FAX,
        in_emp_row.EMAIL
        );
        PIPE ROW(out_emp_row);
    END LOOP;
    CLOSE cur;
    RETURN;
END;
/

    
SELECT * FROM TABLE(BORN_AFTER());
/

-- 4.1 Basic Stored Procedure
-- Task – Create a stored procedure that selects the first and last names of all the employees.

CREATE OR REPLACE PROCEDURE EMP_FULL_NAME (S OUT SYS_REFCURSOR)
IS
FNAME EMPLOYEE.FIRSTNAME%TYPE;
LNAME EMPLOYEE.LASTNAME%TYPE;
BEGIN
OPEN S FOR
SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END;
/

DECLARE
S SYS_REFCURSOR;
FNAME EMPLOYEE.FIRSTNAME%TYPE;
LNAME EMPLOYEE.LASTNAME%TYPE;
BEGIN
    EMP_FULL_NAME(S);
    LOOP
        FETCH S INTO FNAME, LNAME;
        EXIT WHEN S%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(FNAME || ' ' || LNAME);
    END LOOP;
    CLOSE S;
END;
/
    

-- 4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.

CREATE OR REPLACE PROCEDURE UPDATE_EMPLOYEE 
(E_ID IN EMPLOYEE.EMPLOYEEID%TYPE, E_TITLE EMPLOYEE.TITLE%TYPE, E_ADD EMPLOYEE.ADDRESS%TYPE, E_CITY EMPLOYEE.CITY%TYPE, E_STATE EMPLOYEE.STATE%TYPE, E_COUNTRY EMPLOYEE.COUNTRY%TYPE, E_ZIP EMPLOYEE.POSTALCODE%TYPE, E_PHONE EMPLOYEE.PHONE%TYPE, E_FAX EMPLOYEE.FAX%TYPE, E_EMAIL EMPLOYEE.EMAIL%TYPE)
IS
BEGIN
    UPDATE EMPLOYEE SET EMPLOYEEID = E_ID, TITLE = E_TITLE, ADDRESS = E_ADD, CITY = E_CITY, STATE = E_STATE, COUNTRY = E_COUNTRY, POSTALCODE = E_ZIP, PHONE = E_PHONE, FAX = E_FAX, EMAIL = E_EMAIL
    WHERE EMPLOYEEID = E_ID;
    COMMIT;
END;
/

BEGIN
UPDATE_EMPLOYEE(10, 'Senior Engineer', '921 7 ST NW', 'Mountain View', 'CA','UNITED STATES','94039', '+1 (650) 444-2222','+1 (322) 434-9898', 'tony@StarkIndustries.com');
END;
/

-- Task – Create a stored procedure that returns the managers of an employee.

CREATE OR REPLACE PROCEDURE RET_MANAGERS 
(E_ID IN EMPLOYEE.EMPLOYEEID%TYPE)
IS
E_FNAME EMPLOYEE.FIRSTNAME%TYPE;
E_LNAME EMPLOYEE.LASTNAME%TYPE;
M_FNAME EMPLOYEE.FIRSTNAME%TYPE;
M_LNAME EMPLOYEE.LASTNAME%TYPE;
BEGIN
    SELECT  e.FIRSTNAME, e.LASTNAME, ee.FIRSTNAME, ee.LASTNAME
    INTO E_FNAME, E_LNAME, M_FNAME, M_LNAME
    FROM EMPLOYEE e, EMPLOYEE ee
    WHERE  e.EMPLOYEEID = E_ID AND e.REPORTSTO = ee.EMPLOYEEID;
    DBMS_OUTPUT.PUT_LINE('EMPLOYEE: ' || E_FNAME || ' ' || E_LNAME || ' MANAGER: '|| M_FNAME || ' ' || M_LNAME);
END;
/

BEGIN
RET_MANAGERS(10);
END;
/


-- 4.3 Stored Procedure Output Parameters
-- Task – Create a stored procedure that returns the name and company of a customer.

CREATE OR REPLACE PROCEDURE NAME_COMP 
(S OUT SYS_REFCURSOR, CUST_ID IN CUSTOMER.CUSTOMERID%TYPE)
IS
BEGIN 
    OPEN S FOR
    SELECT FIRSTNAME, LASTNAME, COMPANY
    FROM CUSTOMER
    WHERE CUSTOMERID = CUST_ID;
END;
/

DECLARE
S SYS_REFCURSOR;
C_FNAME CUSTOMER.FIRSTNAME%TYPE;
C_LNAME CUSTOMER.LASTNAME%TYPE;
C_COMPANY CUSTOMER.COMPANY%TYPE;
BEGIN
    NAME_COMP(S, 60);
    FETCH S INTO C_FNAME, C_LNAME, C_COMPANY;
    DBMS_OUTPUT.PUT_LINE('CUSTOMER: ' || C_FNAME || ' ' || C_LNAME || ' COMPANY: ' || C_COMPANY);
    CLOSE S;
END;
/

-- 5.0 Transactions
-- Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).

CREATE OR REPLACE PROCEDURE DELETE_INVOICE 
(I_ID IN INVOICE.INVOICEID%TYPE)
IS
InID_Exists INTEGER;

BEGIN
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    
    SELECT COUNT(INVOICEID) INTO InID_Exists FROM INVOICELINE
    WHERE INVOICEID = I_ID;
    
    IF InID_Exists > 0 THEN
    
        DELETE FROM INVOICELINE WHERE INVOICEID = I_ID;
        DELETE FROM INVOICE WHERE INVOICEID = I_ID;
        DBMS_OUTPUT.PUT_LINE('DELETED INVOICEID: ' || I_ID);
        
    ELSE
    DBMS_OUTPUT.PUT_LINE('FAILED TO DELETE INVOICE');
   
    END IF;
    COMMIT;

EXCEPTION 
    WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('FAILED TO DELETE INVOICE');
    ROLLBACK;
END;
/

BEGIN
DELETE_INVOICE(401);
END;
/

-- 6.1
-- Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.

CREATE OR REPLACE TRIGGER e_insert_trig
    AFTER 
    INSERT ON EMPLOYEE
    BEGIN
        DBMS_OUTPUT.PUT_LINE('EMPLOYEE INSERT TRIGGER FIRED');
    END;
/
SET SERVEROUTPUT OFF;

INSERT INTO EMPLOYEE VALUES (11, 'Messi', 'Lionel', 'Soccer Player', 1, '24-JUN-87', '01-JAN-05', '922 7 ST NW', 'ORLANDO', 'RO', 'SOUTH AMERICA', '32780', '+1 (321) 212-1919', '+1 (321) 111-1212', 'lionel@chinookcorp.com');
COMMIT;

/
-- Task – Create an after update trigger on the album table that fires after a row is inserted in the table

CREATE OR REPLACE TRIGGER a_update_trig
    AFTER UPDATE ON ALBUM
    BEGIN
        DBMS_OUTPUT.PUT_LINE('ALBUM UPDATE TRIGGER FIRED');
    END;
/


UPDATE ALBUM SET TITLE = 'Restless And Wild' WHERE ALBUMID = 3;
COMMIT;

-- Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.

CREATE OR REPLACE TRIGGER c_delete_trig
    AFTER DELETE ON CUSTOMER
    BEGIN
        DBMS_OUTPUT.PUT_LINE('CUSTOMER DELETED TRIGGER FIRED');
    END;
/

DELETE FROM CUSTOMER WHERE CUSTOMERID = 60;
ROLLBACK;

-- 7.1
-- Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.

SELECT i.INVOICEID AS INVOICEID, c.FIRSTNAME AS FIRSTNAME, c.LASTNAME AS LASTNAME
FROM CUSTOMER c
INNER JOIN INVOICE i ON i.CUSTOMERID = c.CUSTOMERID;
/

--7.2
-- Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.

SELECT i.INVOICEID AS INVOICEID, c.CUSTOMERID AS CUSTOMERID, c.FIRSTNAME AS FIRSTNAME, c.LASTNAME AS LASTNAME, i.TOTAL AS TOTAL
FROM CUSTOMER c
FULL OUTER JOIN INVOICE i ON i.CUSTOMERID = c.CUSTOMERID;
/

-- 7.3
-- Task – Create a right join that joins album and artist specifying artist name and title.

SELECT l.TITLE AS ALBUM_TITLE, a.NAME AS ARIST_NAME FROM ALBUM l
RIGHT JOIN ARTIST a ON a.ARTISTID = l.ARTISTID;
/

-- 7.4
-- Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.

SELECT l.TITLE AS ALBUM_TITLE, a.NAME AS ARIST_NAME FROM ALBUM l
CROSS JOIN ARTIST a
ORDER BY a.NAME ASC;
/

-- 7.5
-- Task – Perform a self-join on the employee table, joining on the reportsto column.

SELECT (e.LASTNAME || ', ' || e.FIRSTNAME) AS EMPLOYEE_1, (ee.LASTNAME || ', ' || ee.FIRSTNAME) AS EMPLOYEE_2, e.REPORTSTO 
FROM EMPLOYEE e, EMPLOYEE ee
WHERE e.REPORTSTO = ee.REPORTSTO;
/

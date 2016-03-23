DROP TABLE Scart_Prod;
DROP TABLE Prod_Supp;
DROP TABLE Supplier;
drop table CART;
drop table Users;
drop table product_product;
DROP TABLE Product;

drop sequence userseq;

create SEQUENCE userSeq
start with 3
INCREMENT BY 1 MAXVALUE 5000 CYCLE;


create SEQUENCE cartSeq
start with 3
INCREMENT BY 1 MAXVALUE 5000 CYCLE;

CREATE TABLE USERS (
 userID number primary key,
 userName varchar2(255) ,
 password varchar2(255),
 type varchar2(255),
 name varchar2(255),
 address varchar2(255)

);
create Table CART
(
cartID number primary key,
totalPrice number,
foreign key(cartID) references USERS(userID)
);

CREATE TABLE Product (
    PR_ID INTEGER PRIMARY KEY,
    descr VARCHAR2(25),
    price DECIMAL(4,2),
--     shelfnum VARCHAR2(25),
    qtyOnShelf INTEGER 
);

CREATE TABLE Scart_Prod (
    SPR_ID INTEGER,
    cartID number,
    PR_ID INTEGER,
    pr_QTY INTEGER ,
    PRIMARY KEY (SPR_ID),
    FOREIGN KEY (cartID) REFERENCES Cart (cartID),
    FOREIGN KEY (PR_ID) REFERENCES Product (PR_ID)
);

CREATE TABLE Supplier (
    SUP_ID INTEGER PRIMARY KEY,
    company VARCHAR2(25),
    address VARCHAR2(25),
    phone VARCHAR2(25)
);

CREATE TABLE Prod_Supp (
    PR_ID INTEGER,
    SUP_ID INTEGER,
    PRIMARY KEY (PR_ID, SUP_ID),
    FOREIGN KEY (PR_ID) REFERENCES Product (PR_ID),
    FOREIGN KEY (SUP_ID) REFERENCES Supplier (SUP_ID)
);


bn  /* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  cytex
 * Created: 17-Mar-2016
 */
drop table Users;
drop table Cart;

drop sequence userseq;
drop sequence cartSeq;


CREATE TABLE USERS (
 userID number primary key,
 userName varchar2(255) ,
 password varchar2(255),
 type varchar2(255),
 name varchar2(255),
 address varchar2(255)

);
create Table Cart
(
cartID number primary key,
foreign key(cartID) references USERS(userID)
);

create SEQUENCE userSeq
start with 3
INCREMENT BY 1 MAXVALUE 5000 CYCLE;

create SEQUENCE cartSeq
start with 3
INCREMENT BY 1 MAXVALUE 5000 CYCLE;
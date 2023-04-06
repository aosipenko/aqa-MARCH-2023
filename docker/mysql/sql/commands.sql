# PersonID unique, PK, auto
# LastName
# FirstName
# Title
# Gender

desc MY_TABLE;

create table MY_TABLE(
                         ITEM_ID int UNIQUE AUTO_INCREMENT PRIMARY KEY ,
                         TEST_KEY VARCHAR(20),
                         TEST_VALUE VARCHAR(20)
);

select * from MY_TABLE;

drop table MY_TABLE;

alter table MY_TABLE add TEST_NEW_COLUMN VARCHAR(30);

insert into MY_TABLE (ITEM_ID, TEST_KEY, TEST_VALUE, TEST_NEW_COLUMN)
VALUES (10, 'test_key', 'test_value', 'test column!');

insert into MY_TABLE (TEST_KEY, TEST_VALUE, TEST_NEW_COLUMN)
VALUES ('test_key', 'test_value', 'test column!');

select * from MY_TABLE;
select * from MY_TABLE where MY_TABLE.TEST_NEW_COLUMN LIKE '%other%' OR ITEM_ID < 10 AND ITEM_ID > 4;

insert into MY_TABLE (TEST_KEY, TEST_VALUE, TEST_NEW_COLUMN)
VALUES ('another key', 'so is this', 'another one!');

select * from MY_TABLE where MY_TABLE.ITEM_ID BETWEEN 2 AND 10;

select * from MY_TABLE where MY_TABLE.ITEM_ID IN (1,2,3,4,5,6,10,12,44);

delete from MY_TABLE where ITEM_ID < 19;

update MY_TABLE set TEST_KEY = 'updated_key' where ITEM_ID = 19;
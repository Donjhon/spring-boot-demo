declare cnt number;
begin 
   ---查询要创建的表是否存在
   select count(*) into cnt from user_tables where table_name = 'MYDEMO'; 
   ---如果存在则删除该表
   if cnt>0 then
      execute immediate 'drop table MYDEMO';
   else
    execute immediate 'CREATE TABLE MYDEMO (
    id INT NOT NULL PRIMARY KEY,
    myname VARCHAR(1000) DEFAULT NULL,
    age INT DEFAULT NULL
    )';
  end if;
end;

/

INSERT INTO MYDEMO (id,myname,age) VALUES (1,'tom',10);
INSERT INTO MYDEMO (id,myname,age) VALUES (2,'jack',11);
INSERT INTO MYDEMO (id,myname,age) VALUES (3,'tim',12);
INSERT INTO MYDEMO (id,myname,age) VALUES (4,'json',13);
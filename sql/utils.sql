insert into user(username,password) values('lengchu','1234');
select * from user;
update user set email='admin@lenchu.cn' where username='admin';
delete from user where username='1122';
alter table book modify bid varchar(32);
desc book;
select * from book;
delete from user where email='11';
update book set uid='admin' where bid='4';

alter table book drop primary key;
alter table book modify column bid varchar(32) not null,add primary key(bid,uid);
CREATE TABLE USER(
	username VARCHAR(20) PRIMARY KEY,
	PASSWORD VARCHAR(32),
	email VARCHAR(32),
	createtime VARCHAR(20)
);

CREATE TABLE book(
	bid varchar(32) not null,
	bookname VARCHAR(50),
	author VARCHAR(32),
	price NUMERIC(10,2),
	buytime VARCHAR(20),
	uid varchar(20),
	constraint primarykey PRIMARY KEY (bid,uid),
	constraint owner foreign key(uid) references user(username)
);
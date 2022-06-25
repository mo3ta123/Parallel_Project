create database Amazon;
use Amazon;
create table User(
	User_ID INTEGER NOT NULL auto_increment,
    Phone VARCHAR(12),
    Name  VARCHAR(30) NOT NULL,
    Password VARCHAR(20) NOT NULL,
    Balance INTEGER default 0,
    User_type enum('admin','client'),
    primary key (User_ID)
);

CREATE TABLE Item(
    Item_ID INTEGER NOT NULL auto_increment,
    Price INTEGER NOT NULL,
    Name varchar(20),
    Amount_available INTEGER default 0,
    Category VARCHAR(20),
    IMG_URL VARCHAR(100),
    Item_description varchar(300),
    primary key(Item_ID)
);

CREATE TABLE Transaction(
	Transaction_ID INTEGER NOT NULL auto_increment,
    money_Amount INTEGER default 0,
    Transaction_type ENUM('Buy','Deposit') NOT NULL,
    Transaction_date DATETIME,
    User_ID INTEGER NOT NULL,
    primary key(Transaction_ID),
    FOREIGN KEY(User_ID) REFERENCES User(User_ID)
);

CREATE TABLE Holds(
    Transaction_ID INTEGER NOT NULL,
    Item_ID INTEGER NOT NULL,
    Amount INTEGER NOT NULL,
    foreign key(Transaction_ID) REFERENCES Transaction(Transaction_ID),
    foreign key(Item_ID) REFERENCES Item(Item_ID),
    PRIMARY KEY(Item_ID,Transaction_ID)
);




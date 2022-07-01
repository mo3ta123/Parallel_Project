drop database amazon;
create database Amazon;
use Amazon;
create table User(
	Name  VARCHAR(30) NOT NULL,
    Phone VARCHAR(12),
    email varchar (40),
    Password VARCHAR(20) NOT NULL,
    Balance FLOAT default 0,
    User_type enum('admin','client') default 'client',
    primary key (Name)
);

CREATE TABLE Item(
    Item_ID INTEGER NOT NULL auto_increment,
    Price INTEGER NOT NULL,
    Name varchar(50),
    Amount_available INTEGER default 0,
    Category VARCHAR(20),
    IMG_URL VARCHAR(500),
    primary key(Item_ID)
);

CREATE TABLE Transaction(
	Transaction_ID INTEGER NOT NULL auto_increment,
    money_Amount INTEGER default 0,
    Transaction_type ENUM('Buy','Deposit') NOT NULL,
    Transaction_date DATETIME,
    User_Name varchar(30) NOT NULL,
    primary key(Transaction_ID),
    FOREIGN KEY(User_Name) REFERENCES User(name)
);

CREATE TABLE Holds(
    Transaction_ID INTEGER NOT NULL,
    Item_ID INTEGER NOT NULL,
    Amount INTEGER NOT NULL,
    foreign key(Transaction_ID) REFERENCES Transaction(Transaction_ID),
    foreign key(Item_ID) REFERENCES Item(Item_ID),
    PRIMARY KEY(Item_ID,Transaction_ID)
	
);

CREATE TABLE cart(
	user_name varchar(30) not null,
    item_id integer not null,
    Amount INTEGER NOT NULL,
    foreign key (user_name) references User(name),
    foreign key(item_id) references item(item_id)
);

INSERT INTO User(Name, Password , User_type) VALUES("ADMIN", "xajfk" , 'admin' );

INSERT INTO item( Name, Amount_available, category, Img_URL, Price) VALUES 
('Camera',20,'Electronics','https://egyptlaptop.com/images/detailed/31/1594281073_IMG_1384580.jpg',5000),
('Phone',10,'Electronics','https://media.4rgos.it/i/Argos/9520608_R_Z001A?w=750&h=440&qlt=70',6000),
('Washing Machine',5,'Electronics','https://cairocart.com/pub/media/catalog/product/cache/f65e4f3f0d55f7c692e033a9434b3a7f/h/o/hoover-washing-machine-fully-automatic-7-kg-in-black-color-dxoc17c3b-ela-zoom.jpg',12000),
('Phone',10,'Electronics','https://media.4rgos.it/i/Argos/9520608_R_Z001A?w=750&h=440&qlt=70',6000),
('Fridge',4,'Electronics','https://cdn.shopify.com/s/files/1/0024/9803/5810/products/523200-Product-0-I-637741141959068251_800x800.jpg?v=1638477869',20000),
('Laptop',2,'Electronics','https://m.media-amazon.com/images/I/718ETwvLVOL._SL1500_.jpg',16000),
('PlayStation',3,'Electronics','https://gmedia.playstation.com/is/image/SIEPDC/ps5-product-thumbnail-01-en-14sep21',16000),
('Apple iPhone 12 Pro Max - 256GB, Blue',5,'Electronics','https:\/\/student.valuxapps.com\/storage\/uploads\/products\/1615440322npwmU.71DVgBTdyLL._SL1500_.jpg',25000);


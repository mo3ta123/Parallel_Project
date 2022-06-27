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
INSERT INTO item( Name, Amount_available, category, Img_URL, Price) VALUES 
('Camera',20,'Electronics','https://egyptlaptop.com/images/detailed/31/1594281073_IMG_1384580.jpg',5000),
('Phone',10,'Electronics','https://media.4rgos.it/i/Argos/9520608_R_Z001A?w=750&h=440&qlt=70',6000),
('Washing Machine',5,'Electronics','https://cairocart.com/pub/media/catalog/product/cache/f65e4f3f0d55f7c692e033a9434b3a7f/h/o/hoover-washing-machine-fully-automatic-7-kg-in-black-color-dxoc17c3b-ela-zoom.jpg',12000),
('Phone',10,'Electronics','https://media.4rgos.it/i/Argos/9520608_R_Z001A?w=750&h=440&qlt=70',6000),
('Fridge',4,'Electronics','https://cdn.shopify.com/s/files/1/0024/9803/5810/products/523200-Product-0-I-637741141959068251_800x800.jpg?v=1638477869',20000),
('Laptop',2,'Electronics','https://m.media-amazon.com/images/I/718ETwvLVOL._SL1500_.jpg',16000),
('PlayStation',3,'Electronics','https://gmedia.playstation.com/is/image/SIEPDC/ps5-product-thumbnail-01-en-14sep21',16000),
('Apple iPhone 12 Pro Max - 256GB, Blue',5,'Electronics','https:\/\/student.valuxapps.com\/storage\/uploads\/products\/1615440322npwmU.71DVgBTdyLL._SL1500_.jpg',25000),
('JBL JBLXTREME2BLUAM Extreme 2 Waterproof ',12,'Electronics',"https:\/\/student.valuxapps.com\/storage\/uploads\/products\/1615440689wYMHV.item_XXL_36330138_142814934.jpeg",5599),
('Samsung 65 Inch 4K Ultra HD Smart Curved TV',5,'Electronics','https:\/\/student.valuxapps.com\/storage\/uploads\/products\/1615441020ydvqD.item_XXL_51889566_32a329591e022.jpeg',11499),
('Nikon FX-format D750 - 24.3 Megapixel',3,'Electronics','https:\/\/student.valuxapps.com\/storage\/uploads\/products\/1615450256e0bZk.item_XXL_7582156_7501823.jpeg',32860),
("Kingston 2.5 inch 240 GB",12,'Electronics',"https://student.valuxapps.com/storage/uploads/products/1615451352LMOAF.item_XXL_23705724_34135503.jpeg",530),
("Stark Kettlebell, 24 kg",32,'GYM','https://student.valuxapps.com/storage/uploads/products/161545152160GOl.item_XXL_39275650_152762070.jpeg',1080),
("Mask to protect the face, 50 pieces",40,"Health","https://student.valuxapps.com/storage/uploads/products/1638734223uyATp.1.jpg	",45),
("Xiaomi Redmi 10 Dual SIM Mobile Phone",23,"Electronics","https://student.valuxapps.com/storage/uploads/products/1638734961565J3.11.jpg",3075),
("Avony Medical Mask","Health","https://student.valuxapps.com/storage/uploads/products/1638734575kfKn8.21.jpg",28);



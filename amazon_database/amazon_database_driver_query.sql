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

INSERT INTO User(Name, Password) VALUES("ADMIN", "xajfk");

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
("Avony Medical Mask",12,"Health","https://student.valuxapps.com/storage/uploads/products/1638734575kfKn8.21.jpg",28),
("Laptop for MacBookpro",37,'Electronics',"https://student.valuxapps.com/storage/uploads/products/1615442168bVx52.item_XXL_36581132_143760083.jpeg",44500),
("Mesh Women's Training Sneakers",36,"clothes","https://student.valuxapps.com/storage/uploads/products/1638737146iLO2c.11.jpg",1606),
("Men's NikeNSWIconFutura T-Shirt",15,"clothes","https://student.valuxapps.com/storage/uploads/products/1638737571de5EF.21.jpg",1085),
("Sony PlayStation 5 Pulse 3D Wireless",22,'Electronics',"https://student.valuxapps.com/storage/uploads/products/16387377980g2kx.11.jpg",1596),
("Sony WI-C200 Wireless Headphones ",25,'Electronics',"https://student.valuxapps.com/storage/uploads/products/1638737964KFEyZ.21.jpg",499),
("Xiaomi Smart Bracelet 5-Black",10,'Electronics',"https://student.valuxapps.com/storage/uploads/products/1638735246ToPmP.21.jpg",444),
("Xiaomi Night Light With Kinetic ",29,'Electronics',"https://student.valuxapps.com/storage/uploads/products/1638738391RrZ5V.21.jpg",400),
("Circular Lighting Ring ",15,'Electronics',"https://student.valuxapps.com/storage/uploads/products/1638738160hkG50.1.jpg",160.64),
("Men's JacketCollagenCollagen Uniform",20,"clothes","https://student.valuxapps.com/storage/uploads/products/1644374518pTaSB.10.jpg",404),
("Patch Pocket Long Sleeve Shirt - Dark Olive",7,"clothes","https://student.valuxapps.com/storage/uploads/products/1644375298PFm8i.14.jpg",225),
("BLUE FRESH & HIGH COLLECTION - Camel",11,"clothes","https://student.valuxapps.com/storage/uploads/products/1644372386y0SzM.4.jpg",110);


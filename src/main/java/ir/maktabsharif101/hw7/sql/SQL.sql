CREATE TABLE IF NOT EXISTS users(
    id serial PRIMARY KEY NOT NULL ,
    fullName varchar(50),
    userName varchar(50) NOT NULL UNIQUE ,
    email varchar(50) NOT NULL UNIQUE NOT NULL ,
    password_ varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS category(
    id serial PRIMARY KEY NOT NULL ,
    categoryName varchar(50) NOT NULL UNIQUE ,
    description varchar(350)
);

CREATE TABLE IF NOT EXISTS brand(
    id serial PRIMARY KEY NOT NULL ,
    brandName varchar(50) UNIQUE NOT NULL ,
    website varchar(50) UNIQUE,
    description varchar(350)
);

CREATE TABLE IF NOT EXISTS shareholder(
    id serial PRIMARY KEY NOT NULL ,
    shareHolderName varchar(50) NOT NULL ,
    phoneNumber varchar(10),
    nationalCode varchar(10) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS product(
    id serial NOT NULL PRIMARY KEY ,
    productName varchar(50),
    createDate varchar(50),
    categoryId int REFERENCES category(id),
    brandId int REFERENCES brand(id)
);

CREATE TABLE IF NOT EXISTS shareholder_brand(
    brandId int REFERENCES brand(id),
    shareHolderId int REFERENCES shareholder(id),
    PRIMARY KEY (brandId,shareHolderId)
);

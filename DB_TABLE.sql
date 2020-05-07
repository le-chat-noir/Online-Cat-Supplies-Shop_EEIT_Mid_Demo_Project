-- 喵星人物資補給站 Servlet + JDBC + Hibernate 版本 SQL
IF DB_ID('LeonPower') IS NULL
    BEGIN
        CREATE DATABASE SCHOOL
		COLLATE Chinese_Taiwan_Stroke_CI_AS
        PRINT 'Database LeonPower created.'
    END
ELSE
    BEGIN
        PRINT 'Database LeonPower already exist.'
    END
GO

USE LeonPower
GO

CREATE TABLE LeonPower.dbo.userProfiles(
	UID	INT PRIMARY KEY NOT NULL IDENTITY(1,1),
	account VARCHAR(20) NOT NULL,
	password NVARCHAR(50) NOT NULL,
	name  NVARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	address NVARCHAR(100),
	phoneNumber VARCHAR(20) NOT NULL,
	accountVerified VARCHAR(10),
	profileImage varbinary(MAX),
	imgBase64 VARCHAR(max),
)
GO

CREATE TABLE LeonPower.dbo.product(
	PID	INT PRIMARY KEY NOT NULL IDENTITY(1,1),
	catID INT NOT NULL,
	pName  NVARCHAR(50) NOT NULL,
	pDescription NVARCHAR(100) NOT NULL, 
	pPrice INT NOT NULL,
	pImage varbinary(MAX) NOT NULL,
	pBase64 VARCHAR(max),
)
GO

CREATE TABLE LeonPower.dbo.Orders(
   orderID INT PRIMARY KEY NOT NULL IDENTITY(1,1),
   userID INT NOT NULL, --REFERENCES LeonPower.dbo.userProfiles(UID),
   totalAmount INT NOT NULL,
   shippingAddress NVARCHAR(100) NOT NULL,
   shippingPhone VARCHAR(20) NOT NULL,
   shippingName NVARCHAR(50) NOT NULL,
   orderNote NVARCHAR(1000),
   payID VARCHAR(30),
   payStatus VARCHAR(10),
)
CREATE TABLE LeonPower.dbo.OrdersDetails(
	orderListId INT PRIMARY KEY NOT NULL IDENTITY(1,1),
	orderID INT NOT NULL REFERENCES LeonPower.dbo.Orders(orderID),
	productID INT NOT NULL,
	productName NVARCHAR(30) NOT NULL,
	quantity INT NOT NULL,
	unitPrice INT NOT NULL,
	subTotal INT NOT NULL,
)

GO

CREATE Table LeonPower.dbo.opinion(
	oid	INT PRIMARY KEY NOT NULL IDENTITY(1,1),
	uid INT,
	userName NVARCHAR(50) NOT NULL,
	userMail  NVARCHAR(50),
	opinionTitle NVARCHAR(100), 
	opinionText NVARCHAR(1000) NOT NULL,
	solution NVARCHAR(1000),
	caseStatus VARCHAR(10),
)
GO

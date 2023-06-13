Create DATABASE NewWebsite1;
GO

USE NewWebsite1;
GO


CREATE TABLE UserS(
[User_id] INT IDENTITY(1,1) PRIMARY KEY,
[User_name] NVARCHAR(50),
Gender NVARCHAR(20),
dob DATE,
Username VARCHAR(60),
id_Admin bit,
PASSWORD VARCHAR(20)
);


CREATE TABLE Category(
Cat_id int IDENTITY(1,1) PRIMARY KEY,
Cat_name nvarchar(50),
Cat_description nvarchar(100)
);

CREATE TABLE News (
News_id int IDENTITY(1,1) Primary Key,
[User_id] int FOREIGN KEY REFERENCES UserS([User_id]),
Cat_id int FOREIGN KEY REFERENCES Category(Cat_id),
News_title nvarchar(100),
News_subtitle nvarchar(200),
News_content nvarchar(MAX),
News_image varchar(50)
);


CREATE TABLE Comment(
Comment_id int IDENTITY(1,1) PRIMARY KEY,
[User_id] int FOREIGN KEY REFERENCES UserS([User_id]),
News_id int FOREIGN KEY REFERENCES News(News_id),
Comment_content nvarchar(MAX)
);

CREATE TABLE [Save] (
Save_id int IDENTITY(1,1) PRIMARY KEY,
[User_id] int FOREIGN KEY REFERENCES UserS([User_id]),
News_id int FOREIGN KEY REFERENCES News(News_id),
)

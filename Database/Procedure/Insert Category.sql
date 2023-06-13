--Insert Category
CREATE PROC insertCate
@name NVARCHAR(50),
@des NVARCHAR(100)
AS
BEGIN
	INSERT INTO dbo.Category
	(
	    Cat_name,
	    Cat_description
	)
	SELECT @name, @des
END
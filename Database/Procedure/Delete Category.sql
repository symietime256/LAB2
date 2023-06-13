--Delete Category
CREATE PROC delCate
@id INT
AS
BEGIN
	DELETE FROM dbo.Category WHERE Cat_id = @id
END